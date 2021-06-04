package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.dto.UserDto;
import com.example.book.publishinghouse.model.User;

import java.util.List;

public interface UserServices {

    User findByEmail(String email,String locale);

    List<User> findAll(String locale);

    User addUser(User user, String locale);

    User updateUser(int id, UserDto user, String locale);

    void delete(int id, String locale);

    User findById(int id, String locale);
}
