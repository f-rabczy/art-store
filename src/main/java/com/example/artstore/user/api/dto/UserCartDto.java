package com.example.artstore.user.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserCartDto {

    private Double totalPrice;
    List<CartBookDto> books;
}
