package com.example.BookAPI.repository;

import com.example.BookAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
