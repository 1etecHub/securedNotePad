package com.notePad.securedNotePad.controller;


import com.notePad.securedNotePad.entity.Note;
import com.notePad.securedNotePad.services.NoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/createNote")
    public ResponseEntity <Note> createNote(
            @RequestBody String content,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        Note createdNote = noteService.createNoteForUser(username, content);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);

        //return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/updateNote/{noteId}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long noteId,
            @RequestBody String content,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            Note updatedNote = noteService.updateNoteForUser(noteId, content, username);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN); // Or NOT_FOUND based on exception
        }
    }

    @DeleteMapping("/deleteNote/{noteId}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            noteService.deleteNoteForUser(noteId, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Or NOT_FOUND based on exception
        }
    }

    @GetMapping("/getNote/{noteId}")
    public ResponseEntity<List<Note>> getNotesForUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<Note> notes = noteService.getNotesForUser(username);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}
