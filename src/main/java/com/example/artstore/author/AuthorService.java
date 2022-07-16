package com.example.artstore.author;

import com.example.artstore.author.api.dto.AuthorCreateDto;
import com.example.artstore.author.api.dto.AuthorWithBooksCreateDto;
import com.example.artstore.author.api.dto.AuthorWithBooksDto;
import com.example.artstore.author.api.dto.AuthorWithoutBooksDto;
import com.example.artstore.author.mapper.AuthorMapper;
import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import com.example.artstore.book.audiobook.Audiobook;
import com.example.artstore.book.audiobook.AudiobookCreator;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.BookCreator;
import com.example.artstore.book.ebook.EBook;
import com.example.artstore.book.ebook.EBookCreator;
import com.example.artstore.book.paperbook.PaperBook;
import com.example.artstore.book.paperbook.PaperBookCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper = AuthorMapper.getInstance();

    public AuthorWithBooksDto getAuthorWithBooks(Long id){
        return authorRepository.findById(id)
                .map(authorMapper::mapToAuthorWithBooksDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public AuthorWithoutBooksDto createAuthorWithoutBooks(AuthorCreateDto createDto){
        Author authorToSave = Author.builder()
                .fullName(createDto.getFullName())
                .build();
        Author savedAuthor = authorRepository.save(authorToSave);
        return authorMapper.mapToAuthorWithoutBooksDto(savedAuthor);
    }

    public AuthorWithBooksDto createAuthorWithBooks(AuthorWithBooksCreateDto createDto){
        BookCreator bookCreator;
        List<Audiobook> audiobooks = new ArrayList<>();
        List<PaperBook> paperBooks = new ArrayList<>();
        List<EBook> eBooks = new ArrayList<>();

        for (AudiobookCreateDto audiobook : createDto.getAudiobooks()) {
            bookCreator = new AudiobookCreator();
            BaseBook basicBook = bookCreator.createBasicBook(audiobook);
            audiobooks.add((Audiobook) basicBook);
        }

        for (EBookCreateDto eBook : createDto.getEBooks()) {
            bookCreator = new EBookCreator();
            BaseBook basicBook = bookCreator.createBasicBook(eBook);
            eBooks.add((EBook) basicBook);
        }

        for (PaperBookCreateDto paperBook : createDto.getPaperBooks()) {
            bookCreator = new PaperBookCreator();
            BaseBook basicBook = bookCreator.createBasicBook((paperBook));
            paperBooks.add((PaperBook) basicBook);
        }

        Author authorToSave = Author.builder()
                .fullName(createDto.getFullName())
                .audiobooks(audiobooks)
                .paperBooks(paperBooks)
                .eBooks(eBooks)
                .build();

        Author save = authorRepository.save(authorToSave);
        return authorMapper.mapToAuthorWithBooksDto(save);
    }
}
