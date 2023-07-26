package com.cristian.notes.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArchiveEnum status = ArchiveEnum.ACTIVE;

    @ManyToMany
    @JoinTable(name = "note_categories",
            joinColumns = @JoinColumn(name = "id_note"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    private List<Category> categories = new ArrayList<>();

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Note() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArchiveEnum getStatus() {
        return status;
    }

    public void setStatus(ArchiveEnum archived) {
        this.status = archived;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", status=" + status +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
