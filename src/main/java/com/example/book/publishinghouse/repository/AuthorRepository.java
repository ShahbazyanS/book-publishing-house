package com.example.book.publishinghouse.repository;

import com.example.book.publishinghouse.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
