package com.openclassrooms.diabetesreportservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth; // ISO (yyyy-MM-dd) depuis patient-service
    private String gender;         // "M" ou "F"
    private String mailingAddress;
    private String phoneNumber;
}
