package com.example.artstore.book.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class QuantityDto {

    @Min(value = 0)
    private Integer quantity;
}
