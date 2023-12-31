package com.mgunawardhana.s3.controller;

import com.mgunawardhana.s3.domain.APIResponse;
import com.mgunawardhana.s3.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By - mgunawardhana
 * Date - 2023-12-31
 * Time - 17.46
 */
@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    private StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<APIResponse> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return storageService.uploadFile(file);
    }

    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource =
    }

}
