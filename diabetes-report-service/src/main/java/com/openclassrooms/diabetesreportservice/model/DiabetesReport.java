package com.openclassrooms.diabetesreportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiabetesReport {
    private Long patientId;
    private String assessment;   // "None", "Borderline", "In Danger", "Early onset"
    private int triggerCount;    // utile pour debug/affichage
}
