package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.Author;

import java.util.Locale;

public interface AuthorServices {

    Author addAuthor(Author author);

    Author findById(int id, String locale);

}
