package com.openclassrooms.noteservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notes")
public class Note {
    @Id
    private String id;       // généré par MongoDB
    private Long patId;
    private String patient;
    private String note;
}
