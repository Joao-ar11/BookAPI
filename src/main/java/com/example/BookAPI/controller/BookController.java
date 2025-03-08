package com.example.BookAPI.controller;

import com.example.BookAPI.controller.dto.BookDto;
import com.example.BookAPI.model.Book;
import com.example.BookAPI.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin
@RestController
@RequestMapping("/book")
@Tag(name = "Books Controller", description = "RESTful Api for managing books")
public record BookController(BookService bookService) {

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list with all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation succesfull")
    })
    public ResponseEntity<List<BookDto>> findAll() {
        List<Book> books = bookService.findAll();
        List<BookDto> booksDto = books.stream().map(BookDto::new).collect(toList());
        return ResponseEntity.ok(booksDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id", description = "Retrieve a specific book based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation succesfull"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(new BookDto(book));
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Create a new book and retrieve the created book's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created succesfully"),
            @ApiResponse(responseCode = "422", description = "Invalid book data provided")
    })
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        Book book = bookService.create(bookDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).body(new BookDto(book));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update the existing data for a book based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated succesfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid book data provided")
    })
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book book = bookService.update(id, bookDto.toModel());
        return ResponseEntity.ok(new BookDto(book));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Delete a existing book based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
