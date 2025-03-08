package com.example.BookAPI.controller.dto;

import com.example.BookAPI.model.Genre;

public record GenreDto(
        Long id,
        String name
) {

    public GenreDto(Genre genre) {
        this(genre.getId(), genre.getName());
    }

    public Genre toModel() {
        Genre model = new Genre();
        model.setId(this.id);
        model.setName(this.name);
        return model;
    }
}
