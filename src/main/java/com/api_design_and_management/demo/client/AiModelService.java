package com.api_design_and_management.demo.client;

import org.springframework.stereotype.Component;

@Component
public class AiModelService {

    public String modelServiceUrl() {
        return "http://localhost:8000/predict/";
    }
}
