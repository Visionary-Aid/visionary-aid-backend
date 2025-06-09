package com.api_design_and_management.demo.controller;

import com.api_design_and_management.demo.service.UploadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final UploadImageService uploadImageService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadImageService.uploadImage(file);
    }
}
