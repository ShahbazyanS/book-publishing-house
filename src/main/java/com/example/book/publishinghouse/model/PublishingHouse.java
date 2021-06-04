package com.example.book.publishinghouse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "pub_house")
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne
    private Contact contact;
    @OneToOne
    private User user;
    @OneToMany
    private List<Book> books;

}
