package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.Book;

import java.util.List;

public interface BookServices {

    Book addBook(Book book);

    void delete(int id, String locale);

    List<Book> findAll(String locale);

    List<Book> findLastBooks(String locale);

    List<Book> findByAuthorId(int id,String locale);

    Book findById(int id, String local);

    List<Book> searchBook(String word);

}
