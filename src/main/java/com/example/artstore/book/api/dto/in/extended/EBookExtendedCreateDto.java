package com.example.artstore.book.api.dto.in.extended;

import com.example.artstore.book.api.dto.in.basic.BookBasicCreateDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EBookExtendedCreateDto extends BookExtendedCreateDto {

    @NotNull
    private Integer pages;
    @NotEmpty(message = "File size cant be empty")
    private String fileSize;
    @NotEmpty(message = "File format cant be empty")
    private String fileFormat;

}
