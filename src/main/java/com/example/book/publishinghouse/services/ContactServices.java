package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.Contact;

import java.util.List;

public interface ContactServices {

    Contact findById(int id, String locale);

    Contact save(Contact contact);

    List<Contact> findAll(String locale);

    Contact update(int id, Contact contact, String locale);

    void delete(int id, String locale);
}
