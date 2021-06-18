package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.PubHouseDto;
import com.example.book.publishinghouse.model.PublishingHouse;
import com.example.book.publishinghouse.model.User;
import com.example.book.publishinghouse.services.PublishingHouseServicesImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pub_house")
public class PublishingHouseController {

    private final PublishingHouseServicesImpl publishingHouseServices;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public PublishingHouse findId(@PathVariable("id") int id, @RequestHeader("Accept-Language") String locale) {
        return publishingHouseServices.findById(id, locale);
    }

    @GetMapping("/all")
    public List<PublishingHouse> findAll() {
        return publishingHouseServices.pubHouses();
    }

    @PostMapping
    public PublishingHouse add(@RequestBody PubHouseDto pubHouseDto) {
        PublishingHouse publishingHouse = modelMapper.map(pubHouseDto, PublishingHouse.class);
        return publishingHouseServices.add(publishingHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id, @RequestHeader("Accept-Language") String locale) {
        publishingHouseServices.delete(id, locale);
        return ResponseEntity.status(HttpStatus.OK).body("Publishing House deleted");
    }

    @PutMapping("/{id}")
    public PublishingHouse update(@PathVariable("id") int id, @RequestBody PubHouseDto pubHouseDto, @RequestHeader("Accept-Language") String locale) {
        PublishingHouse publishingHouse = modelMapper.map(pubHouseDto, PublishingHouse.class);
        return publishingHouseServices.update(id, publishingHouse, locale);
    }

}
