package com.example.artstore.book.api.dto.in.basic;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AudiobookCreateDto extends BookBasicCreateDto {

    @NotEmpty(message = "Audiobook narrator cant be empty")
    private String narrator = "";
    @NotEmpty(message = "Audiobook length cant be empty")
    private String length = "";
}
