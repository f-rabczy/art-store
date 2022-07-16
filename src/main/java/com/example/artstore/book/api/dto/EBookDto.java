package com.example.artstore.book.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EBookDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate publicationDate;
    private String publisher;
    private Integer pages;
    private Double overallRating;
    private String fileSize;
    private String fileFormat;
    private String price;
    private Integer soldUnit;
    private String availability;
}
