package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.AuthorDto;
import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.services.AuthorServicesImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/all")
    public List<Author> getAllAuthors(){
        return authorServices.getAll();
    }
}
