package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.constants.ReturnCode;
import com.example.book.publishinghouse.exceptions.ResourcesNotFoundException;
import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthorServicesImpl implements AuthorServices{

    private final Logger LOGGER = LoggerFactory.getLogger(AuthorServicesImpl.class);

    private final AuthorRepository authorRepository;
    private final MessageSource messageSource;

    @Override
    public Author addAuthor(Author author) {
        Author auth = authorRepository.save(author);
        LOGGER.info(ReturnCode.OK);
        return auth;
    }

    @Override
    public Author findById(int id, String locale) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.author.not.found", null, new Locale(locale))));
        LOGGER.info(ReturnCode.OK);
        return author;
    }
}
