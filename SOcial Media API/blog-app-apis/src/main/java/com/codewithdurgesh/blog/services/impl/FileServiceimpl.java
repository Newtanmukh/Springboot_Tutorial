package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.services.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class FileServiceimpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException{
        return null;
    };

    @Override
    public InputStream getResource(String path, String fileName) throws IOException{
        return null;
    };
}
