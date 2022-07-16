package com.example.artstore.book.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AudiobookDto {

    private Long id;
    private String title;
    private String author;
    private String narrator;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate publicationDate;
    private String isbn;
    private String publisher;
    private Double overallRating;
    private String length;
    private String price;
    private Integer soldUnit;
    private Boolean availability;

}
