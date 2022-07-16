package com.example.artstore.book.api.dto;

import lombok.Data;

@Data
public class BaseBookDto {

    private Long id;
    private String title;
    private String author;
    private Double overallRating;
    private String imageUrl;
    private String isbn;
    private Boolean availability;
}
