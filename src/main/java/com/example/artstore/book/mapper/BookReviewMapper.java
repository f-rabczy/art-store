package com.example.artstore.book.mapper;

import com.example.artstore.book.api.dto.ReviewDto;
import com.example.artstore.book.basebook.BookReview;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;


public class BookReviewMapper  {
    private static BookReviewMapper instance;

    private BookReviewMapper() {
    }

    public static BookReviewMapper getInstance(){
        if(instance == null){
            instance = new BookReviewMapper();
        }
        return instance;
    }

    public ReviewDto mapToBookReviewDto(BookReview bookReview) {
        if ( bookReview == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId( bookReview.getId() );
        reviewDto.setTitle( bookReview.getTitle() );
        reviewDto.setContent( bookReview.getContent() );
        reviewDto.setRating( bookReview.getRating() );

        return reviewDto;
    }
}
