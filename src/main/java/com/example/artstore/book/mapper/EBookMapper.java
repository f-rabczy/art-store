package com.example.artstore.book.mapper;

import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.EBookDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.ebook.EBook;
import com.example.artstore.book.ebook.EBook.EBookBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;


public class EBookMapper  {

    private static EBookMapper instance;

    private EBookMapper() {
    }

    public static EBookMapper getInstance(){
        if(instance == null){
            instance = new EBookMapper();
        }
        return instance;
    }


    public EBookDto mapToEBookDto(EBook eBookEntity) {
        if ( eBookEntity == null ) {
            return null;
        }

        EBookDto eBookDto = new EBookDto();

        eBookDto.setId( eBookEntity.getId() );
        eBookDto.setTitle( eBookEntity.getTitle() );
        eBookDto.setAuthor( eBookEntity.getAuthor() );
        eBookDto.setIsbn( eBookEntity.getIsbn() );
        eBookDto.setPublicationDate( eBookEntity.getPublicationDate() );
        eBookDto.setPublisher( eBookEntity.getPublisher() );
        eBookDto.setPages( eBookEntity.getPages() );
        eBookDto.setOverallRating( eBookEntity.getOverallRating() );
        eBookDto.setFileSize( eBookEntity.getFileSize() );
        eBookDto.setFileFormat( eBookEntity.getFileFormat() );
        eBookDto.setPrice( eBookEntity.getPrice() );
        eBookDto.setSoldUnit( eBookEntity.getSoldUnit() );
        if ( eBookEntity.getAvailability() != null ) {
            eBookDto.setAvailability( String.valueOf( eBookEntity.getAvailability() ) );
        }

        return eBookDto;
    }

    public EBook mapToEBookEntity(EBookCreateDto eBookCreateDto) {
        if ( eBookCreateDto == null ) {
            return null;
        }

        EBookBuilder<?, ?> eBook = EBook.builder();

        eBook.title( eBookCreateDto.getTitle() );
        eBook.author( eBookCreateDto.getAuthor() );
        eBook.isbn( eBookCreateDto.getIsbn() );
        eBook.category( eBookCreateDto.getCategory() );
        eBook.publicationDate( eBookCreateDto.getPublicationDate() );
        eBook.publisher( eBookCreateDto.getPublisher() );
        eBook.price( eBookCreateDto.getPrice() );
        eBook.imageUrl( eBookCreateDto.getImageUrl() );
        eBook.pages( eBookCreateDto.getPages() );
        eBook.fileSize( eBookCreateDto.getFileSize() );
        eBook.fileFormat( eBookCreateDto.getFileFormat() );

        return eBook.build();
    }
}
