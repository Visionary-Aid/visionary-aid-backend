package com.api_design_and_management.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageService {
    String uploadImage(MultipartFile file) throws IOException;
}
