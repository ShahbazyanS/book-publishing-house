package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.Book;
import org.apache.tomcat.jni.Local;

import java.util.List;
import java.util.Locale;

public interface BookServices {

    Book addBook(Book book);

    void delete(int id, String locale);

    List<Book> findAll(String locale);

    List<Book> findByAuthorId(int id,String locale);

    Book findById(int id, String local);

}
