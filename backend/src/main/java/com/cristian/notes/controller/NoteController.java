package com.cristian.notes.controller;

import com.cristian.notes.domain.ArchiveEnum;
import com.cristian.notes.domain.Note;
import com.cristian.notes.dto.NoteDto;
import com.cristian.notes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        LOGGER.info("GET /api/notes");
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Note>> getNote(@PathVariable(name = "id") Long id) {
        LOGGER.info("GET /api/notes/{}", id);
        return new ResponseEntity<>(noteService.getNote(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody @Validated NoteDto note) {
        LOGGER.info("POST /api/notes body: {}", note);
        return new ResponseEntity<>(noteService.createNote(note.title, note.body), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable(name = "id") Long id) {
        LOGGER.info("DELETE /api/notes/{}", id);
        noteService.deleteNote(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(name = "id") Long id, @RequestBody @Validated NoteDto noteUpdated) {
        LOGGER.info("PUT /api/notes/{} body: {}", id, noteUpdated);
        return new ResponseEntity<>(noteService.updateNote(id, noteUpdated.title, noteUpdated.body), HttpStatus.OK);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Note> archiveNote(@PathVariable(name = "id") Long id) {
        LOGGER.info("PUT /api/notes/{}/archive", id);
        return new ResponseEntity<>(noteService.updateNoteState(id, ArchiveEnum.ARCHIVED), HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Note> activateNote(@PathVariable(name = "id") Long id) {
        LOGGER.info("PUT /api/notes/{}/activate", id);
        return new ResponseEntity<>(noteService.updateNoteState(id, ArchiveEnum.ACTIVE), HttpStatus.OK);
    }

    @PutMapping("/{id}/{categoryName}")
    public ResponseEntity<Note> addCategoryToNote(@PathVariable(name = "id") Long id, @PathVariable(name = "categoryName") String categoryName) {
        LOGGER.info("PUT /api/notes/{}/{}", id, categoryName);
        return new ResponseEntity<>(noteService.addCategory(id, categoryName), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
