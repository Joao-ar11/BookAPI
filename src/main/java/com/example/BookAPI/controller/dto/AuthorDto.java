package com.example.BookAPI.controller.dto;

import com.example.BookAPI.model.Author;

public record AuthorDto(
        Long id,
        String name
) {

    public AuthorDto(Author author) {
        this(author.getId(), author.getName());
    }

    public Author toModel() {
        Author model = new Author();
        model.setId(this.id);
        model.setName(this.name);
        return model;
    }
}
