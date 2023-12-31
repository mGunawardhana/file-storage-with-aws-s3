package com.mgunawardhana.s3.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.mgunawardhana.s3.domain.APIResponse;
import com.mgunawardhana.s3.service.StorageService;
import com.mgunawardhana.s3.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final ResponseUtils responseUtils;


    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public StorageServiceImpl(ResponseUtils responseUtils) {
        this.responseUtils = responseUtils;
    }

    @Override
    public ResponseEntity<APIResponse> uploadFile(MultipartFile file) {
        File fileObject = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObject));
        fileObject.delete();
        return responseUtils.wrapSuccess("File Uploaded "+fileName, HttpStatus.OK);
    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3ClientObject = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3ClientObject.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return convertedFile;
    }

    @Override
    public ResponseEntity<APIResponse> deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return responseUtils.wrapSuccess(fileName + " deleted successful!",HttpStatus.OK);
    }


}