package com.example.book.publishinghouse.endpoint;

import com.example.book.publishinghouse.dto.AuthRequest;
import com.example.book.publishinghouse.dto.AuthResponse;
import com.example.book.publishinghouse.dto.UserDto;
import com.example.book.publishinghouse.model.User;
import com.example.book.publishinghouse.security.JwtTokenUtil;
import com.example.book.publishinghouse.services.EmailServiceImpl;
import com.example.book.publishinghouse.services.UserServicesImpl;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServicesImpl userServices;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final EmailServiceImpl emailService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers(@RequestHeader("Accept-Language") String locale) {
        return userServices.findAll(locale);
    }

    @PostMapping("/add")
    public ResponseEntity<User> save(@RequestBody UserDto userDto, @RequestHeader("Accept-Language") String locale, Locale locale1) throws MessagingException, javax.mail.MessagingException {
        if (userDto.getPassword().equals(userDto.getConfirmPassword())) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = modelMapper.map(userDto, User.class);
            user.setActive(false);
            user.setToken(UUID.randomUUID().toString());
            userServices.addUser(user, locale);
            String link = "http://localhost:8080/user/activate?email=" + user.getEmail() + "&token=" + user.getToken();
            emailService.sendHtmlEmil(userDto.getEmail(), "Welcome", user, link, "email/userEmail.html", locale1);
            emailService.sendHtmlEmil("test2021covid@gmail.com", user.getEmail(), user, "http://localhost:8080/admin/user?email=" + user.getEmail(), "email/adminEmail.html", locale1);
            return ResponseEntity.ok(user);

        }
        return null;
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthRequest authRequest, @RequestHeader("Accept-Language") String locale) {
        User user = userServices.findByEmail(authRequest.getEmail(), locale);
        if (user != null) {
            if (user.isActive() && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(AuthResponse.builder()
                        .token(token)
                        .name(user.getEmail())
                        .build());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/activate")
    public User activate(@RequestParam("email") String email, @RequestParam("token") String token, @RequestHeader("Accept-Language") String locale) {
        User user = userServices.findByEmail(email, locale);
        if (user.getToken().equals(token)) {
            user.setActive(true);
            user.setToken("");
            userServices.activate(user);
            return user;
        }
        return null;
    }

    @GetMapping("/{userId}")
    public User userById(@PathVariable("userId") int userId, @RequestHeader("Accept-Language") String locale) {
        return userServices.findById(userId, locale);
    }

    @GetMapping("/email/{email}")
    public User userByEmail(@PathVariable("email") String email, @RequestHeader("Accept-Language") String locale) {
        return userServices.findByEmail(email, locale);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable("userId") int userId, @RequestHeader("Accept-Language") String locale) {
        userServices.delete(userId, locale);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") int userId, @RequestBody UserDto userDto, @RequestHeader("Accept-Language") String locale) {
       return userServices.updateUser(userId, userDto, locale);
    }
}
