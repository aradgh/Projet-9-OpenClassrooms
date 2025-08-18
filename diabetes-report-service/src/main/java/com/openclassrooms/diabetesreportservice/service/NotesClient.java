package com.openclassrooms.diabetesreportservice.service;

import com.openclassrooms.diabetesreportservice.model.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotesClient {

    private final RestTemplate restTemplate;

    @Value("${notes.api.base-url}")
    private String baseUrl;

    public List<NoteDTO> getNotesByPatient(Long patId) {
        ParameterizedTypeReference<List<NoteDTO>> typeRef = new ParameterizedTypeReference<List<NoteDTO>>() {
        };
        ResponseEntity<List<NoteDTO>> response = restTemplate.exchange(
            baseUrl + "/patient/" + patId, HttpMethod.GET, null, typeRef);
        return response.getBody();
    }
}
