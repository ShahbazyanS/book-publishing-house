package com.example.book.publishinghouse.dto;

import com.example.book.publishinghouse.model.Author;
import com.example.book.publishinghouse.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Genre genre;
    @NotNull
    private Author author;
}
