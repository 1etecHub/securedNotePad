package com.notePad.securedNotePad.serviceImplementation;

import com.notePad.securedNotePad.entity.Note;
import com.notePad.securedNotePad.repository.NoteRepository;
import com.notePad.securedNotePad.services.NoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    @Override
    public Note createNoteForUser(String username, String content) {


        Note note = new  Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        return null;
    }
    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            if (note.getOwnerUsername().equals(username)) {
                note.setContent(content);
                return noteRepository.save(note);
            } else {
                throw new IllegalArgumentException("User does not have permission to update this note.");
            }
        } else {
            throw new EntityNotFoundException("Note not found with id: " + noteId);
        }
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            if (note.getOwnerUsername().equals(username)) {
                noteRepository.delete(note);
            } else {
                throw new IllegalArgumentException("User does not have permission to delete this note.");
            }
        } else {
            throw new EntityNotFoundException("Note not found with id: " + noteId);
        }
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        return noteRepository.findByOwnerUsername(username);
    }
}
