package com.example.BookAPI.controller.dto;

import com.example.BookAPI.model.Publisher;

public record PublisherDto(
        Long id,
        String name
) {

    public PublisherDto(Publisher publisher) {
        this(publisher.getId(), publisher.getName());
    }

    public Publisher toModel() {
        Publisher model = new Publisher();
        model.setId(this.id);
        model.setName(this.name);
        return model;
    }
}
