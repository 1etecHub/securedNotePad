package com.notePad.securedNotePad.services;

import com.notePad.securedNotePad.entity.Note;

import java.util.List;

public interface NoteService {

    Note createNoteForUser(String username, String content);
    Note updateNoteForUser(Long noteId, String content, String username);
    void deleteNoteForUser(Long noteId, String username);

    List<Note> getNotesForUser(String username);
}
