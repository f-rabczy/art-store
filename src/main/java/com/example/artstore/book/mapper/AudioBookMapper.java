package com.example.artstore.book.mapper;

import com.example.artstore.book.api.dto.AudiobookDto;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.audiobook.Audiobook;
import com.example.artstore.book.audiobook.Audiobook.AudiobookBuilder;
import com.example.artstore.book.basebook.BaseBook;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;


public class AudioBookMapper  {

    private static AudioBookMapper instance;

    private AudioBookMapper() {
    }

    public static AudioBookMapper getInstance(){
        if(instance == null){
            instance = new AudioBookMapper();
        }
        return instance;
    }

    public AudiobookDto mapToAudioBookDto(Audiobook audiobookEntity) {
        if ( audiobookEntity == null ) {
            return null;
        }

        AudiobookDto audiobookDto = new AudiobookDto();

        audiobookDto.setId( audiobookEntity.getId() );
        audiobookDto.setTitle( audiobookEntity.getTitle() );
        audiobookDto.setAuthor( audiobookEntity.getAuthor() );
        audiobookDto.setNarrator( audiobookEntity.getNarrator() );
        audiobookDto.setPublicationDate( audiobookEntity.getPublicationDate() );
        audiobookDto.setIsbn( audiobookEntity.getIsbn() );
        audiobookDto.setPublisher( audiobookEntity.getPublisher() );
        audiobookDto.setOverallRating( audiobookEntity.getOverallRating() );
        audiobookDto.setLength( audiobookEntity.getLength() );
        audiobookDto.setPrice( audiobookEntity.getPrice() );
        audiobookDto.setSoldUnit( audiobookEntity.getSoldUnit() );
        audiobookDto.setAvailability( audiobookEntity.getAvailability() );

        return audiobookDto;
    }

    public Audiobook mapToAudiobookEntity(AudiobookCreateDto audiobookCreateDto) {
        if ( audiobookCreateDto == null ) {
            return null;
        }

        AudiobookBuilder<?, ?> audiobook = Audiobook.builder();

        audiobook.title( audiobookCreateDto.getTitle() );
        audiobook.author( audiobookCreateDto.getAuthor() );
        audiobook.isbn( audiobookCreateDto.getIsbn() );
        audiobook.category( audiobookCreateDto.getCategory() );
        audiobook.publicationDate( audiobookCreateDto.getPublicationDate() );
        audiobook.publisher( audiobookCreateDto.getPublisher() );
        audiobook.price( audiobookCreateDto.getPrice() );
        audiobook.imageUrl( audiobookCreateDto.getImageUrl() );
        audiobook.narrator( audiobookCreateDto.getNarrator() );
        audiobook.length( audiobookCreateDto.getLength() );

        return audiobook.build();
    }
}
