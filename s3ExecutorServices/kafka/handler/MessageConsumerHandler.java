package com.amazons3.amazon.kafka.handler;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazons3.amazon.kafka.event_producer.EventMessageProcessor;

import com.amazons3.amazon.kafka.KafkaConsumer.MessageConsumer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import static com.amazons3.amazon.config.Client.getClient;

@Component
public class MessageConsumerHandler implements EventMessageProcessor {
    AmazonS3 s3client = getClient();
    @Value("${s3.bucketName}")
    private String bucketName;
    @Value("${s3.csvFileName}")
    private String csvFileName;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerHandler.class);


    public void register(String message) throws Exception {


        LOGGER.info(String.format("Message => %s", message));

        if (!s3client.doesBucketExistV2(bucketName)) {
            s3client.createBucket(new CreateBucketRequest(bucketName));
        }

        String[] columns = message.split(",");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(baos);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

        csvPrinter.printRecord(Arrays.asList(columns));


        byte[] csvBytes = baos.toByteArray();


        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(csvBytes.length);
        s3client.putObject(new PutObjectRequest(bucketName, csvFileName, new ByteArrayInputStream(csvBytes), metadata));


        csvPrinter.close();


        // Check the content of the CSV file
        String csvContent = s3client.getObjectAsString(bucketName, csvFileName);
        System.out.println("Content of the CSV file:");
        System.out.println(csvContent);
    }
}