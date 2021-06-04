package com.example.book.publishinghouse.repository;

import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthor(Author author);
}
