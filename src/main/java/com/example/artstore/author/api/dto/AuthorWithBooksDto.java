package com.example.artstore.author.api.dto;

import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.audiobook.Audiobook;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorWithBooksDto {

    private Long id;
    private String fullName;
    private List<BaseBookDto> audiobooks;
    private List<BaseBookDto> paperBooks;
    private List<BaseBookDto> eBooks;

}
