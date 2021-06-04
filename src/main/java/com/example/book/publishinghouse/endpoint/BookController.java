package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.BookDto;
import com.example.book.publishinghouse.exceptions.ResourcesNotFoundException;
import com.example.book.publishinghouse.model.Book;
import com.example.book.publishinghouse.model.User;
import com.example.book.publishinghouse.services.BookServices;
import com.example.book.publishinghouse.services.BookServicesImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookServicesImpl bookServices;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Book> findAll(@RequestHeader("Accept-Language") String locale){
        return bookServices.findAll(locale);
    }

    @GetMapping("/{bookId}")
    public Book findbyId(@PathVariable("bookId") int bookId,@RequestHeader("Accept-Language") String locale){
        return bookServices.findById(bookId, locale);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> findByAuthorId(@PathVariable("authorId") int authorId, @RequestHeader("Accept-Language") String locale){
        return bookServices.findByAuthorId(authorId, locale);
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        return bookServices.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity delete(@PathVariable("bookId") int bookId, @RequestHeader("Accept-Language") String locale){
        bookServices.delete(bookId,locale);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }
}
