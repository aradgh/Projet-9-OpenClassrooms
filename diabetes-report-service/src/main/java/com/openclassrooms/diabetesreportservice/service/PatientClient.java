package com.openclassrooms.diabetesreportservice.service;

import com.openclassrooms.diabetesreportservice.model.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PatientClient {

    private final RestTemplate restTemplate;

    @Value("${patients.api.base-url}")
    private String baseUrl;

    public PatientDTO getPatient(Long id) {
        return restTemplate.getForObject(baseUrl + "/" + id, PatientDTO.class);
    }
}
