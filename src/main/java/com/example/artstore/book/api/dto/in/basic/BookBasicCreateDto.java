package com.example.artstore.book.api.dto.in.basic;

import com.example.artstore.book.basebook.BookType;
import com.example.artstore.book.bookcategory.CategoryType;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookBasicCreateDto {

    @NotEmpty(message = "Title cant be empty")
    protected String title;
    @NotEmpty(message = "Author cant be empty")
    protected String author;
    @NotEmpty(message = "Isbn cant be empty")
    protected String isbn;
    @NotNull
    protected CategoryType category;
    @NotNull
    protected LocalDate publicationDate;
    @NotEmpty(message = "Publisher cant be empty")
    protected String publisher;
    @NotEmpty(message = "Price cant be empty")
    protected String price;
    @NotEmpty(message = "Image url cant be empty")
    protected String imageUrl;
    @NotNull
    protected BookType bookType;
}
