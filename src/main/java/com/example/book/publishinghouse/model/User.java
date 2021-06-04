package com.example.book.publishinghouse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean active = false;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;
    private String token;
}