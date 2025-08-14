package com.openclassrooms.noteservice.repository;

import com.openclassrooms.noteservice.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByPatId(Long patId);
}
