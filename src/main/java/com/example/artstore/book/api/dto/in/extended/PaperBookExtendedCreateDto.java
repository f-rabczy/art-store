package com.example.artstore.book.api.dto.in.extended;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class PaperBookExtendedCreateDto extends BookExtendedCreateDto {

    @NotNull
    private Integer pages;
}
