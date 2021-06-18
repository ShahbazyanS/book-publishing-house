package com.example.book.publishinghouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "pub_house_id")
    private PublishingHouse publishingHouse;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name= "author_id")
    private Author author;
}
