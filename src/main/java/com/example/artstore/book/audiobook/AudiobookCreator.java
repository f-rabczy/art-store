package com.example.artstore.book.audiobook;

import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.api.dto.in.basic.BookBasicCreateDto;
import com.example.artstore.book.api.dto.in.extended.AudiobookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.BookExtendedCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.BookCreator;

public class AudiobookCreator extends BookCreator {

    @Override
    public BaseBook createBasicBook(BookBasicCreateDto bookBasicCreateDto) {
        AudiobookCreateDto createDto = (AudiobookCreateDto) bookBasicCreateDto;
        return Audiobook.builder()
                .title(createDto.getTitle())
                .author(createDto.getAuthor())
                .isbn(createDto.getIsbn())
                .category(createDto.getCategory())
                .publicationDate(createDto.getPublicationDate())
                .publisher(createDto.getPublisher())
                .price(createDto.getPrice())
                .imageUrl(createDto.getImageUrl())
                .narrator(createDto.getNarrator())
                .length(createDto.getLength())
                .availability(false)
                .build();
    }

    @Override
    public BaseBook createExtendedBook(BookExtendedCreateDto bookExtendedCreateDto) {
        AudiobookExtendedCreateDto extendedCreateDto = (AudiobookExtendedCreateDto) bookExtendedCreateDto;
        return Audiobook.builder()
                .title(extendedCreateDto.getTitle())
                .author(extendedCreateDto.getAuthor())
                .isbn(extendedCreateDto.getIsbn())
                .category(extendedCreateDto.getCategory())
                .publicationDate(extendedCreateDto.getPublicationDate())
                .publisher(extendedCreateDto.getPublisher())
                .price(extendedCreateDto.getPrice())
                .imageUrl(extendedCreateDto.getImageUrl())
                .narrator(extendedCreateDto.getNarrator())
                .length(extendedCreateDto.getLength())
                .overallRating(extendedCreateDto.getOverallRating())
                .soldUnit(extendedCreateDto.getSoldUnit())
                .ratingsNumber(extendedCreateDto.getRatingsNumber())
                .ratingsSum(extendedCreateDto.getRatingsSum())
                .availability(false)
                .build();
    }
}
