package com.cristian.notes.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    @NotEmpty(message = "Category name may not be empty")
    @Size(max = 50)
    public String name;

    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
