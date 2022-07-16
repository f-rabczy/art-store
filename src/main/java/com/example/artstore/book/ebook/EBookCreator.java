package com.example.artstore.book.ebook;

import com.example.artstore.book.api.dto.in.basic.BookBasicCreateDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.BookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.EBookExtendedCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.BookCreator;

public class EBookCreator extends BookCreator {

    @Override
    public BaseBook createBasicBook(BookBasicCreateDto bookBasicCreateDto) {
        EBookCreateDto createDto = (EBookCreateDto) bookBasicCreateDto;
        return EBook.builder()
                .title(createDto.getTitle())
                .author(createDto.getAuthor())
                .isbn(createDto.getIsbn())
                .category(createDto.getCategory())
                .publicationDate(createDto.getPublicationDate())
                .publisher(createDto.getPublisher())
                .price(createDto.getPrice())
                .imageUrl(createDto.getImageUrl())
                .pages(createDto.getPages())
                .fileSize(createDto.getFileSize())
                .fileFormat(createDto.getFileFormat())
                .availability(false)
                .build();
    }

    @Override
    public BaseBook createExtendedBook(BookExtendedCreateDto bookExtendedCreateDto) {
        EBookExtendedCreateDto extendedCreateDto = (EBookExtendedCreateDto) bookExtendedCreateDto;
        return EBook.builder()
                .title(extendedCreateDto.getTitle())
                .author(extendedCreateDto.getAuthor())
                .isbn(extendedCreateDto.getIsbn())
                .category(extendedCreateDto.getCategory())
                .publicationDate(extendedCreateDto.getPublicationDate())
                .publisher(extendedCreateDto.getPublisher())
                .price(extendedCreateDto.getPrice())
                .imageUrl(extendedCreateDto.getImageUrl())
                .pages(extendedCreateDto.getPages())
                .fileSize(extendedCreateDto.getFileSize())
                .fileFormat(extendedCreateDto.getFileFormat())
                .overallRating(extendedCreateDto.getOverallRating())
                .soldUnit(extendedCreateDto.getSoldUnit())
                .ratingsNumber(extendedCreateDto.getRatingsNumber())
                .ratingsSum(extendedCreateDto.getRatingsSum())
                .availability(false)
                .build();
    }
}
