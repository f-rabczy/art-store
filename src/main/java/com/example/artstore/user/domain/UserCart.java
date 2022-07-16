package com.example.artstore.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
@Data
@NoArgsConstructor
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice = 0D;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CartBook> cartBookList = new ArrayList<>();

    public void addBooks(List<CartBook> cartBookList){
        this.cartBookList.addAll(cartBookList);
        calculateTotalPrice(cartBookList);
    }

    private void calculateTotalPrice(List<CartBook> cartBookList){
        double sum = cartBookList.stream()
                .mapToDouble(CartBook::getPrice)
                .sum();
        totalPrice += sum;
    }

}
