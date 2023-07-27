package com.cristian.notes.dto;

import com.cristian.notes.domain.Note;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class NoteDto {
    public Long id;

    @Size(max = 50)
    public String title;
    @Size(max = 2000)
    public String body;
    public String status = "ACTIVE";
    public String createdAt;
    public String lastEdited;
    public List<String> categories = new ArrayList<>();

    public NoteDto() {
    }

    public NoteDto(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.body = note.getBody();
        this.status = note.getStatus().toString();
        this.createdAt = note.getCreatedAt();
        this.lastEdited = note.getLastEdited();
        note.getCategories().forEach(c -> categories.add(c.getName()));
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", archive='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", lastEdited='" + lastEdited + '\'' +
                ", categories=" + categories +
                '}';
    }
}
