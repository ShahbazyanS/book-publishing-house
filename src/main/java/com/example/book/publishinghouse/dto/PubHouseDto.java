package com.example.book.publishinghouse.dto;

import com.example.book.publishinghouse.model.Book;
import com.example.book.publishinghouse.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PubHouseDto {

    @NotBlank
    private String name;
    @NotNull
    private Contact contact;
    @NotNull
    private List<Book> books;
}
