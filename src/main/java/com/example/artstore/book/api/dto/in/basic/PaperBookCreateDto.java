package com.example.artstore.book.api.dto.in.basic;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class PaperBookCreateDto extends BookBasicCreateDto{

    @NotNull
    private Integer pages = 0;
}
