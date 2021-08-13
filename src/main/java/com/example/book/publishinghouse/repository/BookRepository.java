package com.example.book.publishinghouse.repository;

import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.model.Book;
import com.example.book.publishinghouse.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByGenre(Genre genre);

    @Query(value = "SELECT * FROM books ORDER BY id DESC  LIMIT 9",
            nativeQuery = true)
    List<Book> findLastBooks();

    @Query(value = "SELECT * FROM books WHERE `title` LIKE %:word% OR description LIKE %:word%", nativeQuery = true)
    List<Book> searchProduct(@Param("word") String word);
}
