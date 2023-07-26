package com.cristian.notes.dto;

import com.cristian.notes.domain.ArchiveEnum;
import jakarta.validation.constraints.Size;

public class NoteDto {

    @Size(max = 50)
    public String title;
    @Size(max = 2000)
    public String body;

    public String archive = "ACTIVE";

    @Override
    public String toString() {
        return "NoteDto{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", archive=" + archive +
                '}';
    }
}
