package com.example.book.publishinghouse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String surname;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String bio;
    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
