package com.example.artstore.book.paperbook;

import com.example.artstore.book.api.dto.in.basic.BookBasicCreateDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.BookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.PaperBookExtendedCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.BookCreator;

public class PaperBookCreator extends BookCreator {

    @Override
    public BaseBook createBasicBook(BookBasicCreateDto bookBasicCreateDto) {
        PaperBookCreateDto createDto = (PaperBookCreateDto) bookBasicCreateDto;
        return PaperBook.builder()
                .title(createDto.getTitle())
                .author(createDto.getAuthor())
                .isbn(createDto.getIsbn())
                .category(createDto.getCategory())
                .publicationDate(createDto.getPublicationDate())
                .publisher(createDto.getPublisher())
                .price(createDto.getPrice())
                .imageUrl(createDto.getImageUrl())
                .pages(createDto.getPages())
                .availability(false)
                .build();
    }

    @Override
    public BaseBook createExtendedBook(BookExtendedCreateDto bookExtendedCreateDto) {
        PaperBookExtendedCreateDto extendedCreateDto = (PaperBookExtendedCreateDto) bookExtendedCreateDto;
        return PaperBook.builder()
                .title(extendedCreateDto.getTitle())
                .author(extendedCreateDto.getAuthor())
                .isbn(extendedCreateDto.getIsbn())
                .category(extendedCreateDto.getCategory())
                .publicationDate(extendedCreateDto.getPublicationDate())
                .publisher(extendedCreateDto.getPublisher())
                .price(extendedCreateDto.getPrice())
                .imageUrl(extendedCreateDto.getImageUrl())
                .pages(extendedCreateDto.getPages())
                .overallRating(extendedCreateDto.getOverallRating())
                .soldUnit(extendedCreateDto.getSoldUnit())
                .ratingsNumber(extendedCreateDto.getRatingsNumber())
                .ratingsSum(extendedCreateDto.getRatingsSum())
                .availability(false)
                .build();
    }

}
