package com.api_design_and_management.demo.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AudioController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String fastApiUrl = "http://localhost:8000/audio/"; // غيّر الـ port لو مختلف

    @GetMapping("/audio/{filename}")
    public ResponseEntity<byte[]> getAudioFromFastApi(@PathVariable String filename) {
        try {
            String url = fastApiUrl + filename;

            ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
                headers.setContentDispositionFormData("inline", filename);
                return new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audio file not found on FastAPI");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error fetching audio: " + e.getMessage());
        }
    }
}