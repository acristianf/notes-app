package com.cristian.notes.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "created_at")
    private String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    @Column(name = "last_edited")
    private String lastEdited;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "notes_categories",
            joinColumns = @JoinColumn(name = "id_note"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    @JsonManagedReference
    private Set<Category> categories = new HashSet<>();

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Note(String title, String body, List<Category> categories) {
        this.title = title;
        this.body = body;
        this.categories.addAll(categories);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addCategory(Category cat) {
        categories.add(cat);
    }

    public void removeCategory(Category cat) {
        categories.remove(cat);
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", status=" + status +
                '}';
    }

}
