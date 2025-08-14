package com.openclassrooms.noteservice.service;

import com.openclassrooms.noteservice.model.Note;
import com.openclassrooms.noteservice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesByPatId(Long patId) {
        return noteRepository.findByPatId(patId);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }
}
