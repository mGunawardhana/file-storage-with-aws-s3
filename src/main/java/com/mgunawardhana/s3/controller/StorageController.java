package com.mgunawardhana.s3.controller;

import com.mgunawardhana.s3.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public String uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return storageService.uploadFile(file);
    }


}
