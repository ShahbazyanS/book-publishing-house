package com.example.book.publishinghouse.security;

import com.example.book.publishinghouse.model.User;
import com.example.book.publishinghouse.services.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserDetailServiceImpl implements UserDetailsService {


    private final UserServicesImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String locale = "en";
        User user = userService.findByEmail(s, locale);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new CurrentUser(user);
    }
}