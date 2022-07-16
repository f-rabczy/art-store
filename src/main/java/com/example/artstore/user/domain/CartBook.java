package com.example.artstore.user.domain;


import com.example.artstore.book.basebook.BookType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CART_BOOKS")
@Data
@NoArgsConstructor
public class CartBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PRICE")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_TYPE")
    private BookType bookType;
}
