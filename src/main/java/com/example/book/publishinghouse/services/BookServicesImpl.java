package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.constants.ReturnCode;
import com.example.book.publishinghouse.exceptions.ResourcesNotFoundException;
import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.model.Book;
import com.example.book.publishinghouse.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BookServicesImpl implements BookServices {

    private final Logger LOGGER = LoggerFactory.getLogger(BookServicesImpl.class);

    private final BookRepository bookRepository;
    private final MessageSource messageSource;
    private final AuthorServices authorServices;

    @Override
    public Book addBook(Book book) {
        Book book1 = bookRepository.save(book);
        LOGGER.info(ReturnCode.OK);
        return book1;
    }

    @Override
    public void delete(int id, String locale) {
        bookRepository.delete(bookRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.contact.not.found", null, new Locale(locale)))));
        LOGGER.info(ReturnCode.OK);
    }

    @Override
    public List<Book> findAll(String locale) {
        List<Book> allBooks = bookRepository.findAll();
        if (allBooks.isEmpty()){
            LOGGER.info(messageSource.getMessage(
                    "error.messages.null.value", null, new Locale(locale)));
        }
        LOGGER.info(ReturnCode.OK);
        return allBooks;
    }

    @Override
    public List<Book> findLastBooks(String locale) {
        List<Book> allBooks = bookRepository.findLastBooks();
        if (allBooks.isEmpty()){
            LOGGER.info(messageSource.getMessage(
                    "error.messages.null.value", null, new Locale(locale)));
        }
        LOGGER.info(ReturnCode.OK);
        return allBooks;
    }

    @Override
    public List<Book> findByAuthorId(int id, String locale) {
        Author author = authorServices.findById(id, locale);
        List<Book> allByAuthor = bookRepository.findAllByAuthor(author);
        if (author != null && !allByAuthor.isEmpty()){
            LOGGER.info(messageSource.getMessage(
                    "error.messages.null.value", null, new Locale(locale)));
        }
        LOGGER.info(ReturnCode.OK);
        return allByAuthor;
    }

    @Override
    public Book findById(int id, String locale) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.book.not.found", null, new Locale(locale))));
        LOGGER.info(ReturnCode.OK);
        return book;
    }

    @Override
    public List<Book> searchBook(String word) {
        return bookRepository.searchProduct(word);
    }

}
