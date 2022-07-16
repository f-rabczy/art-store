package com.example.artstore.book.mapper;

import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.basebook.BaseBook;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.processing.Generated;


public class BaseBookMapper {
    private static BaseBookMapper instance;

    private BaseBookMapper() {
    }

    public static BaseBookMapper getInstance(){
        if(instance == null){
            instance = new BaseBookMapper();
        }
        return instance;
    }

    public BaseBookDto mapToBookBasicDto(BaseBook bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }

        BaseBookDto baseBookDto = new BaseBookDto();

        baseBookDto.setId( bookEntity.getId() );
        baseBookDto.setTitle( bookEntity.getTitle() );
        baseBookDto.setAuthor( bookEntity.getAuthor() );
        baseBookDto.setOverallRating( bookEntity.getOverallRating() );
        baseBookDto.setImageUrl( bookEntity.getImageUrl() );
        baseBookDto.setIsbn( bookEntity.getIsbn() );
        baseBookDto.setAvailability( bookEntity.getAvailability() );

        return baseBookDto;
    }
}
