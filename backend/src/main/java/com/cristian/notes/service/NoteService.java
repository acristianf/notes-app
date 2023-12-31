package com.cristian.notes.service;

import com.cristian.notes.domain.ArchiveEnum;
import com.cristian.notes.domain.Category;
import com.cristian.notes.domain.Note;
import com.cristian.notes.dto.NoteDto;
import com.cristian.notes.repository.CategoryRepository;
import com.cristian.notes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    private final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public NoteService(NoteRepository noteRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    public List<NoteDto> getAllNotes() {
        LOGGER.info("get all notes");
        List<Note> notes = (List<Note>) noteRepository.findAll();
        List<NoteDto> noteDtos = new ArrayList<>();
        notes.forEach(n -> noteDtos.add(new NoteDto(n)));
        return noteDtos;
    }

    public Optional<Note> getNote(Long id) {
        LOGGER.info("get note {}", id);
        return noteRepository.findById(id);
    }

    public Note createNote(String title, String body, List<String> categories) {
        LOGGER.info("create note with title '{}', body '{}' and categories {}", title, body, categories);
        List<Category> categoryList = new ArrayList<>();
        categories.forEach(cat -> categoryRepository.findByName(cat)
                .ifPresentOrElse(
                        categoryList::add,
                        () -> categoryList.add(categoryService.createCategory(cat))));
        return noteRepository.save(new Note(title, body, categoryList));
    }

    public void deleteNote(Long id) {
        LOGGER.info("delete note {}", id);
        noteRepository.deleteById(id);
    }

    public Note updateNote(Long id, String title, String body, List<String> categories) {
        LOGGER.info("update note {} with title '{}' and body '{}'", id, title, body);
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        note.setTitle(title);
        note.setBody(body);
        note.setLastEdited(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        note.removeAllCategories();
        categories.forEach(c -> this.addCategory(note, c));
        return noteRepository.save(note);
    }

    public Note updateNoteState(Long id, ArchiveEnum state) {
        LOGGER.info("update note {} with state '{}'", id, state);
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        note.setStatus(state);
        return noteRepository.save(note);
    }

    public void addCategory(Note n, String categoryName) {
        LOGGER.info("add category '{}' to note {}", categoryName, n.getId());
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        Category category = categoryOptional.orElse(new Category(categoryName));
        n.addCategory(category);
        categoryRepository.save(category);
    }

    public Note addCategory(Long id, String categoryName) {
        LOGGER.info("add category '{}' to note {}", categoryName, id);
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        Category category = categoryOptional.orElse(new Category(categoryName));
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        note.addCategory(category);
        categoryRepository.save(category);
        return noteRepository.save(note);
    }

    public Note removeCategory(Long id, String categoryName) {
        LOGGER.info("remove category '{}' from note {}", categoryName, id);
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        Category category = categoryOptional.orElseThrow(() -> new NoSuchElementException("No note found with name '" + categoryName + "'."));
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note note = noteOptional.orElseThrow(() -> noteNotFound(id));
        note.removeCategory(category);
        categoryRepository.save(category);
        return noteRepository.save(note);
    }

    private NoSuchElementException noteNotFound(Long id) {
        throw new NoSuchElementException("Note with id " + id + " not found.");
    }
}
