package com.example.artstore.book.util;

import com.example.artstore.book.basebook.BaseBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DtoValidatorService {

    public void validateIsbn(BaseBook book){
        if(book != null){
            throw new IllegalArgumentException("Book with given ISBN already exists");
        }
    }

    private final List<Integer> allowedRatings = List.of(1,2,3,4,5);

    public void checkIfRatingIsCorrect(Integer rating){
        if(!allowedRatings.contains(rating)){
            throw new IllegalArgumentException("Given rating is not allowed, ratings allowed are [1,2,3,4,5]");
        }
    }
}
