package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.Author;

import java.util.List;

public interface AuthorServices {

    Author addAuthor(Author author);

    Author findById(int id, String locale);

    List<Author> getAll();

}
