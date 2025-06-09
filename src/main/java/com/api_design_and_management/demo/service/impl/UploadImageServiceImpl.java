package com.api_design_and_management.demo.service.impl;

import com.api_design_and_management.demo.client.AiModelService;
import com.api_design_and_management.demo.service.UploadImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadImageServiceImpl implements UploadImageService {
    public String uploadImage(MultipartFile file) throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        AiModelService fastApi = new AiModelService();
        String fastApiUrl = fastApi.modelServiceUrl();

        byte[] fileBytes = file.getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, requestEntity, String.class);

        return response.getBody();
    }
}
