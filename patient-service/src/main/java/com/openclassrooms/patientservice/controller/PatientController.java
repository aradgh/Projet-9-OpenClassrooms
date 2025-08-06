package com.openclassrooms.patientservice.controller;

import com.openclassrooms.patientservice.model.Patient;
import com.openclassrooms.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @PutMapping
    public Patient update(@RequestBody @Valid Patient patient) {
        return patientService.save(patient);
    }

    @DeleteMapping
    public void delete(@RequestBody Patient patient) {
        patientService.deleteById(patient.getId());
    }


}
