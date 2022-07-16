package com.example.artstore.user.api.dto;

import com.example.artstore.book.basebook.BookType;
import lombok.Data;

@Data
public class CartBookDto {

    private String name;
    private String author;
    private Double price;
    private BookType bookType;
}
