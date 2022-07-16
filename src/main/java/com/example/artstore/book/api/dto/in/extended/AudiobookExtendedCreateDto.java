package com.example.artstore.book.api.dto.in.extended;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudiobookExtendedCreateDto extends BookExtendedCreateDto {

    @NotEmpty(message = "Audiobook narrator cant be empty")
    private String narrator;
    @NotEmpty(message = "Audiobook length cant be empty")
    private String length;
}
