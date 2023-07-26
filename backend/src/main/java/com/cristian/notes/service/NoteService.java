package com.cristian.notes.service;

import com.cristian.notes.domain.ArchiveEnum;
import com.cristian.notes.domain.Category;
import com.cristian.notes.domain.Note;
import com.cristian.notes.repository.CategoryRepository;
import com.cristian.notes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    private final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
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
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        note.setTitle(title);
        note.setBody(body);
        note.setLastEdited(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return noteRepository.save(note);
    }

    public Note updateNoteState(Long id, ArchiveEnum state) {
        LOGGER.info("update note {} with state '{}'", id, state);
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        note.setStatus(state);
        return noteRepository.save(note);
    }

    public Note addCategory(Long id, String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        Category category = categoryOptional.orElse(new Category(categoryName));
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        category.addNote(note);
        note.addCategory(categoryRepository.save(category));
        return noteRepository.save(note);
    }

    private NoSuchElementException noteNotFound(Long id) {
        throw new NoSuchElementException("Note with id " + id + " not found.");
    }
}
