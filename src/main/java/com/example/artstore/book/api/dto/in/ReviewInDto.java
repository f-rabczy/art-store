package com.example.artstore.book.api.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewInDto {

    private String title;
    private String content;
    private Integer rating;
}
