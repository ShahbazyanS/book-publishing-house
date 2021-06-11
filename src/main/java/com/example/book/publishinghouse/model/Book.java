package com.example.book.publishinghouse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String picUrl;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "pub_house_id")
    private PublishingHouse publishingHouse;
    @ManyToOne
    private Author author;
}
