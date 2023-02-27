package com.amazons3.amazon.service;

import com.amazonaws.services.s3.AmazonS3;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<String> getFileContents(AmazonS3 client,String BucketName) throws IOException;

}
