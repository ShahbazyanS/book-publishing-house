package com.example.book.publishinghouse.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ModelMapConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}