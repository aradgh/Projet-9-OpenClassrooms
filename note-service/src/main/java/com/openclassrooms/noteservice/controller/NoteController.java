package com.openclassrooms.noteservice.controller;

import com.openclassrooms.noteservice.model.Note;
import com.openclassrooms.noteservice.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/patient/{patId}")
    public List<Note> getNotesByPatient(@PathVariable("patId") Long patId) {
        return noteService.getNotesByPatId(patId);
    }

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteService.save(note);
    }
}
