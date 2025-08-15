package com.openclassrooms.diabetesreportservice.controller;

import com.openclassrooms.diabetesreportservice.model.DiabetesReport;
import com.openclassrooms.diabetesreportservice.service.DiabetesReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class DiabetesReportController {

    private final DiabetesReportService reportService;

    @GetMapping("/{patientId}")
    public DiabetesReport getReport(@PathVariable("patientId") Long patientId) {
        return reportService.generateReport(patientId);
    }
}
