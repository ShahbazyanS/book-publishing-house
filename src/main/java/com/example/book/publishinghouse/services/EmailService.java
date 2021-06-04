package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.User;

import javax.mail.MessagingException;
import java.util.Locale;

public interface EmailService {

    void sendHtmlEmil(String to, String subject, User user, String link, String templateName, Locale locale) throws MessagingException;

}