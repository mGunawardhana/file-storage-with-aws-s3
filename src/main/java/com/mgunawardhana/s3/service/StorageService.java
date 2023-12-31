package com.mgunawardhana.s3.service;

import com.mgunawardhana.s3.domain.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created By - mgunawardhana
 * Date - 2023-12-31
 * Time - 17.23
 */

public interface StorageService {
    /**
     * This method is used to upload a file to the storage.
     *
     * @param file This is the file that needs to be uploaded.
     * @return String This returns the name of the uploaded file.
     */
    ResponseEntity<APIResponse> uploadFile(MultipartFile file);

    /**
     * This method is used to download a file from the storage.
     *
     * @param fileName This is the name of the file that needs to be downloaded.
     * @return byte[] This returns the content of the downloaded file in byte array format.
     */
    byte[] downloadFile(String fileName);

    /**
     * This method is used to convert a MultipartFile into a File.
     *
     * @param file This is the MultipartFile that needs to be converted.
     * @return File This returns the converted File.
     */
    File convertMultiPartFileToFile(MultipartFile file);

    /**
     * This method is used to delete a file from the storage.
     *
     * @param fileName This is the name of the file that needs to be deleted.
     * @return String This returns the name of the deleted file.
     */
    ResponseEntity<APIResponse> deleteFile(String fileName);
}
