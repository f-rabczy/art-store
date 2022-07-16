package com.example.artstore.author.mapper;

import com.example.artstore.author.Author;
import com.example.artstore.author.api.dto.AuthorWithBooksDto;
import com.example.artstore.author.api.dto.AuthorWithoutBooksDto;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.audiobook.Audiobook;
import com.example.artstore.book.ebook.EBook;
import com.example.artstore.book.mapper.BaseBookMapper;
import com.example.artstore.book.paperbook.PaperBook;

import java.util.ArrayList;
import java.util.List;


public class AuthorMapper{

    private static AuthorMapper instance;

    private AuthorMapper() {
    }

    public static AuthorMapper getInstance(){
        if(instance == null){
            instance = new AuthorMapper();
        }
        return instance;
    }

    private final BaseBookMapper baseBookMapper = BaseBookMapper.getInstance();

    public AuthorWithoutBooksDto mapToAuthorWithoutBooksDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorWithoutBooksDto authorWithoutBooksDto = new AuthorWithoutBooksDto();

        authorWithoutBooksDto.setId( author.getId() );
        authorWithoutBooksDto.setFullName( author.getFullName() );

        return authorWithoutBooksDto;
    }

    public AuthorWithBooksDto mapToAuthorWithBooksDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorWithBooksDto authorWithBooksDto = new AuthorWithBooksDto();

        authorWithBooksDto.setId( author.getId() );
        authorWithBooksDto.setFullName( author.getFullName() );
        authorWithBooksDto.setAudiobooks( audiobookListToBaseBookDtoList( author.getAudiobooks() ) );
        authorWithBooksDto.setPaperBooks( paperBookListToBaseBookDtoList( author.getPaperBooks() ) );
        authorWithBooksDto.setEBooks( eBookListToBaseBookDtoList( author.getEBooks() ) );

        return authorWithBooksDto;
    }

    protected List<BaseBookDto> audiobookListToBaseBookDtoList(List<Audiobook> list) {
        if ( list == null ) {
            return null;
        }

        List<BaseBookDto> list1 = new ArrayList<BaseBookDto>( list.size() );
        for ( Audiobook audiobook : list ) {
            list1.add( baseBookMapper.mapToBookBasicDto( audiobook ) );
        }

        return list1;
    }

    protected List<BaseBookDto> paperBookListToBaseBookDtoList(List<PaperBook> list) {
        if ( list == null ) {
            return null;
        }

        List<BaseBookDto> list1 = new ArrayList<BaseBookDto>( list.size() );
        for ( PaperBook paperBook : list ) {
            list1.add( baseBookMapper.mapToBookBasicDto( paperBook ) );
        }

        return list1;
    }

    protected List<BaseBookDto> eBookListToBaseBookDtoList(List<EBook> list) {
        if ( list == null ) {
            return null;
        }

        List<BaseBookDto> list1 = new ArrayList<BaseBookDto>( list.size() );
        for ( EBook eBook : list ) {
            list1.add( baseBookMapper.mapToBookBasicDto( eBook ) );
        }

        return list1;
    }
}
