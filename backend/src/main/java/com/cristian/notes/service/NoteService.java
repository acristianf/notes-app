package com.cristian.notes.service;

import com.cristian.notes.domain.ArchiveEnum;
import com.cristian.notes.domain.Note;
import com.cristian.notes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NoteService {

    private final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        LOGGER.info("get all notes");
        return (List<Note>) noteRepository.findAll();
    }

    public Optional<Note> getNote(Long id) {
        LOGGER.info("get note {}", id);
        return noteRepository.findById(id);
    }

    public Note createNote(String title, String body) {
        LOGGER.info("create note with title '{}' and body '{}'", title, body);
        return noteRepository.save(new Note(title, body));
    }

    public void deleteNote(Long id) {
        LOGGER.info("delete note {}", id);
        noteRepository.deleteById(id);
    }

    public Note updateNote(Long id, String title, String body) {
        LOGGER.info("update note {} with title '{}' and body '{}'", id, title, body);
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(NoSuchElementException::new);
        note.setTitle(title);
        note.setBody(body);
        return noteRepository.save(note);
    }

    public Note updateNoteState(Long id, ArchiveEnum state) {
        LOGGER.info("update note {} with state '{}'", id, state);
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(NoSuchElementException::new);
        note.setStatus(state);
        return noteRepository.save(note);
    }
}
