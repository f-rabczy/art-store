package com.example.artstore.book.api.dto.in.extended;

import com.example.artstore.book.bookcategory.CategoryType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookExtendedCreateDto {

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
    protected Double overallRating;
    protected Double ratingsNumber;
    protected Double ratingsSum;
    protected Integer soldUnit;
}
