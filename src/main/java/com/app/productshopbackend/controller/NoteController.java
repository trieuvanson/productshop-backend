package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Note;
import com.app.productshopbackend.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    @Autowired
    NoteRepo noteRepo;

    @GetMapping("/")
    private List<Note> getNotes()  {
        return noteRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id)    {
        Note Note = noteRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Note not exist " + id));
        return ResponseEntity.ok(Note);
    }

    @PostMapping("/")
    public Note PostNote(@RequestBody Note Note) {
        Note.setCreate_at(new Date());
        Note.setUpdate_at(new Date());
        return noteRepo.save(Note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> PutNote(@RequestBody Note Note, @PathVariable Long id)  {
        Note newNote = noteRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note not exist " + id));
        newNote.setTitle(Note.getTitle());
        newNote.setContent(Note.getContent());
        newNote.setUser_id(Note.getUser_id());
        newNote.setUpdate_at(new Date());
        Note updateNote = noteRepo.save(newNote);
        return ResponseEntity.ok(updateNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNote(@PathVariable Long id)    {
        Note Note = noteRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note not exist " + id));
        noteRepo.delete(Note);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
