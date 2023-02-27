package com.amazons3.amazon.config;


import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import static com.amazons3.amazon.config.AppConstants.*;


public class Client {


    public static final AmazonS3 getClient() {

        /*AWSCredentials credentials = new BasicAWSCredentials(
                AWS_ACCESS_KEY_ID,
                AWS_SECRET_ACCESS_KEY
        );*/


//uncomment this
        /*AWSCredentials credentials = new BasicSessionCredentials(
                AWS_ACCESS_KEY_ID,
                AWS_SECRET_ACCESS_KEY,
                AWS_SESSION_TOKEN
        );*/




        /*ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        AmazonS3 s3client = new AmazonS3Client(credentials, clientConfig);*/

        //uncomment this
        /*AmazonS3 s3client = AmazonS3ClientBuilder
                            .standard()
                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                            .withRegion("us-east-1")
                            .build();*/


        return AmazonS3ClientBuilder.standard().withRegion("ap-south-1").withCredentials
                (new AWSCredentialsProviderChain(new DefaultAWSCredentialsProviderChain())).build();

        //return s3client;
    }
}
