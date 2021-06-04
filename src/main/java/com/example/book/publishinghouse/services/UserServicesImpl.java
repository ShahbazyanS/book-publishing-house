package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.constants.ReturnCode;
import com.example.book.publishinghouse.dto.UserDto;
import com.example.book.publishinghouse.exceptions.DuplicateValueException;
import com.example.book.publishinghouse.exceptions.ResourcesNotFoundException;
import com.example.book.publishinghouse.model.User;
import com.example.book.publishinghouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServicesImpl.class);

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Override
    public User findByEmail(String email, String locale) {
        User byEmail = userRepository.findByEmail(email);
        if (byEmail == null) {
            LOGGER.info(messageSource.getMessage(
                    "error.messages.user.not.found", null, new Locale(locale)));
        }
        LOGGER.info(ReturnCode.OK);
        return byEmail;
    }

    @Override
    public List<User> findAll(String locale) {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            LOGGER.info(messageSource.getMessage(
                    "error.messages.null.value", null, new Locale(locale)));
        }
        LOGGER.info(ReturnCode.OK);
        return allUsers;
    }

    @Override
    public User addUser(User user, String locale) {
        List<User> users = userRepository.findAll();
        for (User user1 : users) {
            if (user1.getEmail().equals(user.getEmail())) {
                LOGGER.info(ReturnCode.SERVER_ERROR);
                throw new DuplicateValueException(messageSource.getMessage(
                        "duplicate.email", null, new Locale(locale)));
            }
        }
        LOGGER.info(ReturnCode.OK);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, UserDto user, String locale) {
        User userDb = userRepository.save(userRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(
                        messageSource.getMessage(
                                "error.messages.user.not.found", null, new Locale(locale)))));
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setPassword(user.getPassword());
        return this.addUser(userDb, locale);
    }

    @Override
    public void delete(int id, String locale) {
        userRepository.delete(userRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.user.not.found", null, new Locale(locale)))));
        LOGGER.info(ReturnCode.OK);
    }

    @Override
    public User findById(int id, String locale) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.user.not.found", null, new Locale(locale))));
        LOGGER.info(ReturnCode.OK);
        return user;
    }
}
