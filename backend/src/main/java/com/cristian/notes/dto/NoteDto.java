package com.cristian.notes.dto;

import com.cristian.notes.domain.ArchiveEnum;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class NoteDto {

    @Size(max = 50)
    public String title;
    @Size(max = 2000)
    public String body;

    public String archive = "ACTIVE";

    public List<String> categories = new ArrayList<>();

    @Override
    public String toString() {
        return "NoteDto{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", archive=" + archive +
                '}';
    }
}
