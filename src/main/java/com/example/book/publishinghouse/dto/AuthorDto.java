package com.example.book.publishinghouse.dto;

import com.example.book.publishinghouse.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private Gender gender;
    @NotBlank
    private String bio;
}
