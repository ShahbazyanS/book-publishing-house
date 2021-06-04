package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.AuthorDto;
import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.services.AuthorServicesImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorServicesImpl authorServices;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Author findById(@PathVariable("id") int id,@RequestHeader("Accept-Language") String locale){
        return authorServices.findById(id, locale);
    }

    @PostMapping
    public Author addAuthor(@RequestBody AuthorDto authorDto){
        Author author = modelMapper.map(authorDto, Author.class);
        return authorServices.addAuthor(author);
    }
}