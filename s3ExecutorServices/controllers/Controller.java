package com.amazons3.amazon.controllers;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import com.amazons3.amazon.gateway.Channelgateway;
import com.amazons3.amazon.payloads.ApiResponse;
import com.amazons3.amazon.payloads.PayloadClass;
import com.amazons3.amazon.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


import java.util.List;

import static com.amazons3.amazon.config.Client.getClient;

@RestController
@RequestMapping("/s3")
public class Controller {


    AmazonS3 s3client = getClient();

    @Autowired
    FileService fileService;

    @Autowired
    Channelgateway channelgateway;


    //give you the names of the Buckets.
    @GetMapping("/listBuckets")
    public ResponseEntity<?> getBucketNames(){
        List<String>buckets = s3client.listBuckets().stream().map(Bucket::getName).toList();
        return new ResponseEntity<>(buckets, HttpStatus.OK);
    }


    //get list of objects inside the bucket
    @GetMapping("/{bucketName}")
    public ResponseEntity<?> getBucketDetails(@PathVariable String bucketName){
            boolean doesbucketExist = s3client.doesBucketExist(bucketName);

        if(!doesbucketExist){
            return new ResponseEntity<>(new ApiResponse("Bucket with thus name does not exist",false),HttpStatus.NOT_FOUND);
        }


        ObjectListing objectListing = s3client.listObjects(bucketName);
        List<String> objects = objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).filter(e->e.endsWith(".csv")).filter(e->e.contains("/2022-10-02/")).toList();
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }


    @GetMapping("/bucket/{bucketName}/date/{date}/ESN/{ESN}")
    public ResponseEntity<?> getCsvFilesByDate(@PathVariable String bucketName, @PathVariable String date, @PathVariable String ESN){
        boolean doesbucketExist = s3client.doesBucketExist(bucketName);
        if(doesbucketExist==false){
            //throw new ResourceNotFoundException("Bucket","Bucket Name",bucketName);
            return new ResponseEntity<>(new ApiResponse("Bucket with thus name does not exist",false),HttpStatus.NOT_FOUND);
        }

        ObjectListing objectListing = s3client.listObjects(bucketName);
        List<String> objects = objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).toList();
        List<String> csvFiles = objects.stream().filter(key -> key.contains("/"+date+"/")).filter(key -> key.endsWith(".csv")).toList();

        List<String> rows = new ArrayList<String>();


        for(String object: csvFiles) {
            /*
            Message<String> esn_message = MessageBuilder.withPayload(ESN).setHeader("ESN", ESN).build();
            Message<AmazonS3> amazons3_message = MessageBuilder.withPayload(s3client).setHeader("Amazons", "Amazons3 client").build();
            Message<String> bucketname_message = MessageBuilder.withPayload(bucketName).setHeader("BucketName", bucketName).build();
            Message<String> object_messages = MessageBuilder.withPayload(object).setHeader("Object Names", "Total Number of objects are :" + String.valueOf(csvFiles.size())).build();
            */

            PayloadClass payloadClass = new PayloadClass(object,ESN,s3client,bucketName);
            Message<PayloadClass> message = MessageBuilder.withPayload(payloadClass).setHeader("Message", "Object, external_Serial_no, client instance, bucket name").build();
            Message<String> replyObject = channelgateway.ChannelInitializer(message);

            /*String row = replyObject.getPayload();
            rows.add(row);*/
        }

        return new ResponseEntity<>(rows, HttpStatus.OK);
    }



    // csv files by date, external serial no.
    //will process and return the content of the object inside the bucket.
    @GetMapping("/getRecords/{BucketName}")
    public ResponseEntity<?> getObjectDetails(@PathVariable String BucketName) throws Exception {

        boolean doesbucketExist = s3client.doesBucketExist(BucketName);
        if(doesbucketExist==false){
            return new ResponseEntity<>(new ApiResponse("Bucket with thus name does not exist",false),HttpStatus.NOT_FOUND);
        }

        List<String>records = fileService.getFileContents(s3client,BucketName);



        return new ResponseEntity<>(records, HttpStatus.OK);
    }

}
