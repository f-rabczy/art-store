package com.example.artstore.author.api.dto;

import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorWithBooksCreateDto {

    private String fullName;
    private List<AudiobookCreateDto> audiobooks;
    private List<EBookCreateDto> eBooks;
    private List<PaperBookCreateDto> paperBooks;

}
