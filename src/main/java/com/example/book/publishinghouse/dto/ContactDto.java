package com.example.book.publishinghouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {

    @NotBlank
    private String address;
    @NotBlank
    private long phone;
    @NotBlank
    private String email;
}
