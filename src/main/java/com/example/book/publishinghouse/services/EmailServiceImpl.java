package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.constants.ReturnCode;
import com.example.book.publishinghouse.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailServiceImpl implements EmailService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Async
    @Override
    public void sendHtmlEmil(String to, String subject, User user, String link, String templateName, Locale locale){
        final Context ctx = new Context(locale);
        ctx.setVariable("user", user);
        ctx.setVariable("url", link);

        final String htmlContent = this.templateEngine.process(templateName, ctx);

        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message; // true = multipart
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        message.setSubject(subject);
        message.setFrom("thymeleaf@example.com");
        message.setTo(to);

        message.setText(htmlContent, true); // true = isHtml
        } catch (MessagingException e) {
            LOGGER.info(ReturnCode.SERVER_ERROR);
            e.printStackTrace();
        }
        // Send mail
        this.javaMailSender.send(mimeMessage);
        LOGGER.info(ReturnCode.OK);
    }
}