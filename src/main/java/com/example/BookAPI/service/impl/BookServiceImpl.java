package com.example.BookAPI.service.impl;

import com.example.BookAPI.model.Book;
import com.example.BookAPI.repository.BookRepository;
import com.example.BookAPI.service.BookService;
import com.example.BookAPI.service.exception.BusinessException;
import com.example.BookAPI.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public Book create(Book newBook) {
        ofNullable(newBook).orElseThrow(() -> new BusinessException("Book provided cannot be null"));
        return bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public Book update(Long id, Book newBook) {
        Book book = findById(id);
        if (!book.getId().equals(newBook.getId())) {
            throw new BusinessException("Update IDs must be the same");
        }

        book.setAuthor(newBook.getAuthor());
        book.setGenre(newBook.getGenre());
        book.setPublisher(newBook.getPublisher());
        book.setName(newBook.getName());
        book.setRelease(newBook.getRelease());
        book.setSynopsis(newBook.getSynopsis());
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }
}
