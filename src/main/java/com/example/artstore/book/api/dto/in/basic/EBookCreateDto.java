package com.example.artstore.book.api.dto.in.basic;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EBookCreateDto extends  BookBasicCreateDto {

    @NotNull
    private Integer pages = 0;
    @NotEmpty(message = "File size cant be empty")
    private String fileSize = "";
    @NotEmpty(message = "File format cant be empty")
    private String fileFormat = "";

}
