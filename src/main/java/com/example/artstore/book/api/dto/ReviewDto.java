package com.example.artstore.book.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String title;
    private String content;
    private Integer rating;
}
