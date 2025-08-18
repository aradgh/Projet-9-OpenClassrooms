package com.openclassrooms.diabetesreportservice.service;

import com.openclassrooms.diabetesreportservice.model.DiabetesReport;
import com.openclassrooms.diabetesreportservice.model.NoteDTO;
import com.openclassrooms.diabetesreportservice.model.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DiabetesReportService {

    // Liste des déclencheurs (tests FR)
    private static final List<String> TRIGGERS = Arrays.asList(
        "hemoglobine a1c", "microalbumine", "taille", "poids",
        "fumeur", "fumeuse", "anormal", "cholesterol", "vertige", "rechute", "reaction", "anticorps"
    );
    private static final String RISK_NONE = "None";
    private static final String RISK_BORDERLINE = "Borderline";
    private static final String RISK_IN_DANGER = "In Danger";
    private static final String RISK_EARLY_ONSET = "Early onset";
    private final PatientClient patientClient;
    private final NotesClient notesClient;

    public DiabetesReport generateReport(Long patientId) {
        PatientDTO patient = patientClient.getPatient(patientId);
        List<NoteDTO> notes = notesClient.getNotesByPatient(patientId);

        int age = computeAge(patient.getDateOfBirth(), LocalDate.now());
        int triggers = countTriggers(notes);

        String risk = assessRisk(age, patient.getGender(), triggers);

        return new DiabetesReport(patientId, risk, triggers);
    }

    private int computeAge(LocalDate birthDate, LocalDate now) {
        if (birthDate == null)
            return 0;
        return Period.between(birthDate, now).getYears();
    }

    private int countTriggers(List<NoteDTO> notes) {
        if (notes == null || notes.isEmpty())
            return 0;

        // Ensemble des déclencheurs distincts trouvés (Un déclencheur présent plusieurs fois ne compte qu’une seule fois)
        Set<String> foundTriggers = new HashSet<>();

        for (NoteDTO n : notes) {
            String normalized = normalize(n.getNote());
            for (String t : TRIGGERS) {
                String trig = normalize(t);
                if (normalized.contains(trig)) {
                    foundTriggers.add(trig);
                }
            }
        }
        return foundTriggers.size();
    }

    private String assessRisk(int age, String gender, int triggers) {

        if (triggers == 0) {
            return RISK_NONE;
        }

        if (isBorderline(age, triggers)) {
            return RISK_BORDERLINE;
        }

        if (isInDanger(age, gender, triggers)) {
            return RISK_IN_DANGER;
        }

        if (isEarlyOnset(age, gender, triggers)) {
            return RISK_EARLY_ONSET;
        }

        return RISK_NONE; // fallback
    }

    private boolean isBorderline(int age, int triggers) {
        return age > 30 && triggers >= 2 && triggers <= 5;
    }

    private boolean isInDanger(int age, String gender, int triggers) {
        if (age < 30) {
            return ("M".equalsIgnoreCase(gender) && triggers >= 3 && triggers < 5) || ("F".equalsIgnoreCase(
                gender) && triggers >= 4 && triggers < 7);
        }
        return triggers == 6 || triggers == 7;
    }

    private boolean isEarlyOnset(int age, String gender, int triggers) {
        if (age < 30) {
            return ("M".equalsIgnoreCase(gender) && triggers >= 5) || ("F".equalsIgnoreCase(gender) && triggers >= 7);
        }
        return triggers >= 8;
    }

    private String normalize(String s) {
        if (s == null)
            return "";
        String tmp = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); // supprime accents
        return tmp.toLowerCase();
    }
}
