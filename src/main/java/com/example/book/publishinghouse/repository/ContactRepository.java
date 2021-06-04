package com.example.book.publishinghouse.repository;

import com.example.book.publishinghouse.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
