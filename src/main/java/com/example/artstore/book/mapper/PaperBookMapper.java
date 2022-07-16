package com.example.artstore.book.mapper;

import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.PaperBookDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.paperbook.PaperBook;
import com.example.artstore.book.paperbook.PaperBook.PaperBookBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;


public class PaperBookMapper {

    private static PaperBookMapper instance;

    private PaperBookMapper() {
    }

    public static PaperBookMapper getInstance(){
        if(instance == null){
            instance = new PaperBookMapper();
        }
        return instance;
    }

    public PaperBookDto mapToPaperBookDto(PaperBook paperBookEntity) {
        if ( paperBookEntity == null ) {
            return null;
        }

        PaperBookDto paperBookDto = new PaperBookDto();

        paperBookDto.setId( paperBookEntity.getId() );
        paperBookDto.setTitle( paperBookEntity.getTitle() );
        paperBookDto.setAuthor( paperBookEntity.getAuthor() );
        paperBookDto.setIsbn( paperBookEntity.getIsbn() );
        paperBookDto.setPublicationDate( paperBookEntity.getPublicationDate() );
        paperBookDto.setPublisher( paperBookEntity.getPublisher() );
        paperBookDto.setPages( paperBookEntity.getPages() );
        paperBookDto.setOverallRating( paperBookEntity.getOverallRating() );
        paperBookDto.setPrice( paperBookEntity.getPrice() );
        paperBookDto.setSoldUnit( paperBookEntity.getSoldUnit() );
        paperBookDto.setAvailability( paperBookEntity.getAvailability() );

        return paperBookDto;
    }

    public PaperBook mapToPaperBookEntity(PaperBookCreateDto paperBookCreateDto) {
        if ( paperBookCreateDto == null ) {
            return null;
        }

        PaperBookBuilder<?, ?> paperBook = PaperBook.builder();

        paperBook.title( paperBookCreateDto.getTitle() );
        paperBook.author( paperBookCreateDto.getAuthor() );
        paperBook.isbn( paperBookCreateDto.getIsbn() );
        paperBook.category( paperBookCreateDto.getCategory() );
        paperBook.publicationDate( paperBookCreateDto.getPublicationDate() );
        paperBook.publisher( paperBookCreateDto.getPublisher() );
        paperBook.price( paperBookCreateDto.getPrice() );
        paperBook.imageUrl( paperBookCreateDto.getImageUrl() );
        paperBook.pages( paperBookCreateDto.getPages() );

        return paperBook.build();
    }
}
