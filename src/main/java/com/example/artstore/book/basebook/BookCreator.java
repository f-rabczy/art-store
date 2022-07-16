package com.example.artstore.book.basebook;

import com.example.artstore.book.api.dto.in.basic.BookBasicCreateDto;
import com.example.artstore.book.api.dto.in.extended.BookExtendedCreateDto;

public abstract class BookCreator {

    public abstract Book createBasicBook(BookBasicCreateDto bookBasicCreateDto);

    public abstract Book createExtendedBook(BookExtendedCreateDto bookExtendedCreateDto);
}
