package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.model.User;
import com.example.book.publishinghouse.services.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserServicesImpl userServices;

    @GetMapping("/user")
    public User userPage(@RequestParam ("email") String email, @RequestHeader("Accept-Language") String locale){
        return userServices.findByEmail(email, locale);
    }
}
