package com.example.book.publishinghouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "pub_house_id")
    private PublishingHouse publishingHouse;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;
    private String token;
}
