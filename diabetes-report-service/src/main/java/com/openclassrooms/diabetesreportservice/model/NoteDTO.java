package com.openclassrooms.diabetesreportservice.model;

import lombok.Data;

@Data
public class NoteDTO {
    private String id;      // _id généré par MongoDB
    private Long patId;
    private String note;     // contenu libre
}
