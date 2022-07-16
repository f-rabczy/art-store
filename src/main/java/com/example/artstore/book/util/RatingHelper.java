package com.example.artstore.book.util;


import com.example.artstore.book.basebook.BaseBook;

import java.util.List;

public class RatingHelper {


    public static BaseBook increaseRating(BaseBook baseBook, Integer rating){
        Double ratingsNumber = baseBook.getRatingsNumber();
        Double ratingsSum = baseBook.getRatingsSum();
        Double overallRating = baseBook.getOverallRating();

        ratingsNumber += 1;
        ratingsSum += rating;
        double newOverall = ratingsSum / ratingsNumber;
        newOverall = Math.round(newOverall);

        baseBook.setOverallRating(newOverall);
        baseBook.setRatingsSum(ratingsSum);
        baseBook.setRatingsNumber(ratingsNumber);
        return baseBook;
    }
}
