package com.example.artstore.author.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorWithoutBooksDto {

    private Long id;
    private String fullName;
}
