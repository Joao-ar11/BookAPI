package com.example.BookAPI.controller.dto;

import com.example.BookAPI.model.Author;
import com.example.BookAPI.model.Book;
import com.example.BookAPI.model.Genre;
import com.example.BookAPI.model.Publisher;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record BookDto(
        Long id,
        String name,
        LocalDate release,
        String synopsis,
        AuthorDto author,
        PublisherDto publisher,
        List<GenreDto> genre
) {

    public BookDto(Book book) {
        this(
                book.getId(),
                book.getName(),
                book.getRelease(),
                book.getSynopsis(),
                ofNullable(book.getAuthor()).map(AuthorDto::new).orElse(null),
                ofNullable(book.getPublisher()).map(PublisherDto::new).orElse(null),
                ofNullable(book.getGenre()).orElse(emptyList()).stream().map(GenreDto::new).collect(toList())
                );
    }

    public Book toModel() {
        Book model = new Book();
        model.setId(this.id);
        model.setName(this.name);
        model.setRelease(this.release);
        model.setSynopsis(this.synopsis);
        model.setAuthor(ofNullable(this.author).map(AuthorDto::toModel).orElse(null));
        model.setPublisher(ofNullable(this.publisher).map(PublisherDto::toModel).orElse(null));
        model.setGenre(ofNullable(this.genre).orElse(emptyList()).stream().map(GenreDto::toModel).collect(toList()));
        return model;
    }
}
