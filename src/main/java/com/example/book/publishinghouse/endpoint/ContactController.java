package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.ContactDto;
import com.example.book.publishinghouse.model.Contact;
import com.example.book.publishinghouse.model.PublishingHouse;
import com.example.book.publishinghouse.services.ContactServicesImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactServicesImpl contactServices;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Contact findById(@PathVariable("id") int id, @RequestHeader("Accept-Language") String locale){
        return contactServices.findById(id, locale);
    }

    @GetMapping
    public List<Contact> findAll(@RequestHeader("Accept-Language") String locale){
        return contactServices.findAll(locale);
    }

    @PostMapping
    public Contact add(@RequestBody ContactDto contactDto){
        Contact contact = modelMapper.map(contactDto, Contact.class);
        return contactServices.save(contact);
    }

    @PutMapping("/{id}")
    public Contact update(@PathVariable("id") int id, @RequestBody ContactDto contactDto, @RequestHeader("Accept-Language") String locale){
        Contact contact = modelMapper.map(contactDto, Contact.class);
        return contactServices.update(id, contact, locale);
    }
}
