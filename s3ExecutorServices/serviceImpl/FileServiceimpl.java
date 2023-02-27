package com.amazons3.amazon.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazons3.amazon.service.FileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceimpl implements FileService {



    //For Testing purposes now, just read the contents of FIRST OBJECT in the bucket and return the contents.

    /*@Override
    public List<String> getFileContents(AmazonS3 client, String BucketName) throws IOException {
        ObjectListing objectListing = client.listObjects(BucketName);
        List<String> objects = objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).toList();

        String objectName = objects.stream()
                .filter(e -> e.endsWith(".csv"))
                .toList().get(1);

        S3Object object = client.getObject(BucketName, objectName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
        String[] headers = reader.readLine().split(",");

        CSVParser parser = CSVFormat.DEFAULT.withHeader(headers).parse(reader);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<String>> callables = new ArrayList<>();
        for (CSVRecord record : parser) {
            callables.add(() -> {
                System.out.println(record.get("external_serial_no"));
                System.out.println(record);
                return record.toString();
            });
        }

        List<Future<String>> futures = null;
        try {
            futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
        }

        List<String> rows = new ArrayList<>();
        for (Future<String> future : futures) {
            try {
                rows.add(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();

        return rows;
    }*/


    @Override
    @Payload
    public List<String> getFileContents(AmazonS3 client,String BucketName) throws IOException {


        ObjectListing objectListing = client.listObjects(BucketName);
        List<String> objects = objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).toList();

        String objectName = objects.stream().filter(e->e.endsWith(".csv")).filter(e->e.contains("/2022-10-02/")).toList().get(1);

System.out.println("objectName: " + objectName);

        S3Object object = client.getObject(BucketName,objectName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
        String[] headers = reader.readLine().split(",");
        //*CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);    use this incase you only want to get the clumn values by the index number, like 0,1 etc.*/
        
        CSVParser parser = CSVFormat.DEFAULT.withHeader(headers).parse(reader);//use the headers. now you can get the column values by their names instead of the index number.
        List<String> rows = new ArrayList<>();

        for (CSVRecord record : parser) {
            System.out.println(record.get("external_serial_no"));
            System.out.println(record);
            //rows.add(record.toString());
        }





        return rows;
    }
}
