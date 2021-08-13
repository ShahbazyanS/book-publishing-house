package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.BookDto;
import com.example.book.publishinghouse.model.Book;
import com.example.book.publishinghouse.services.BookServicesImpl;
import com.example.book.publishinghouse.services.PublishingHouseServicesImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    private final String uploadDir = "D:\\Projects\\book\\upload";

    private final BookServicesImpl bookServices;
    private final PublishingHouseServicesImpl publishingHouseServices;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Book> findAll(@RequestHeader("Accept-Language") String locale){
        return bookServices.findAll(locale);
    }

    @GetMapping("/last9")
    public List<Book> findLastBooks(@RequestHeader("Accept-Language") String locale) {
        return bookServices.findLastBooks(locale);
    }

    @GetMapping("/{bookId}")
    public Book findById(@PathVariable("bookId") int bookId, @RequestHeader("Accept-Language") String locale){
        return bookServices.findById(bookId, locale);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> findByAuthorId(@PathVariable("authorId") int authorId, @RequestHeader("Accept-Language") String locale){
        return bookServices.findByAuthorId(authorId, locale);
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto bookDto){
//        PublishingHouse byId = publishingHouseServices.byId(bookDto.getPubId());
        Book book = modelMapper.map(bookDto, Book.class);
        book.setPublishingHouse(bookDto.getPublishingHouse());
        return bookServices.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity delete(@PathVariable("bookId") int bookId, @RequestHeader("Accept-Language") String locale){
        bookServices.delete(bookId,locale);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/search/{word}")
    public  List<Book> search(@PathVariable("word") String word){
        return bookServices.searchBook(word);
    }
}
