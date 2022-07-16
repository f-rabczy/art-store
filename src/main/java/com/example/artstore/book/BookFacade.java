package com.example.artstore.book;


import com.example.artstore.book.api.dto.AudiobookDto;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.EBookDto;
import com.example.artstore.book.api.dto.PaperBookDto;
import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.AudiobookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.EBookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.PaperBookExtendedCreateDto;
import com.example.artstore.book.audiobook.AudiobookService;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.ebook.EBookService;
import com.example.artstore.book.paperbook.PaperBookService;
import com.example.artstore.book.util.DtoValidatorService;
import com.example.artstore.user.domain.User;
import com.example.artstore.user.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookFacade {

    private final PaperBookService paperBookService;
    private final EBookService eBookService;
    private final AudiobookService audiobookService;
    private final DtoValidatorService dtoValidatorService;
    private final UserService userService;

    public BaseBookDto createAudioBook(AudiobookCreateDto createDto){
        BaseBook audiobookOptionalByIsbn = audiobookService.getAudiobookOptionalByIsbn(createDto.getIsbn());
        dtoValidatorService.validateIsbn(audiobookOptionalByIsbn);
        return audiobookService.createAudioBook(createDto);
    }

    public AudiobookDto extendCreateAudioBook(AudiobookExtendedCreateDto createDto){
        BaseBook audiobookOptionalByIsbn = audiobookService.getAudiobookOptionalByIsbn(createDto.getIsbn());
        dtoValidatorService.validateIsbn(audiobookOptionalByIsbn);
        return audiobookService.extendedCreateAudioBook(createDto);
    }

    public BaseBookDto createEBook(EBookCreateDto createDto){
        BaseBook eBookByIsbn = eBookService.getEBookByIsbn(createDto.getIsbn());
        dtoValidatorService.validateIsbn(eBookByIsbn);
        return eBookService.createEBook(createDto);
    }

    public EBookDto extendCreateEBook(EBookExtendedCreateDto createDto){
        BaseBook eBookByIsbn = eBookService.getEBookByIsbn(createDto.getIsbn());
        dtoValidatorService.validateIsbn(eBookByIsbn);
        return eBookService.extendedCreateAudioBook(createDto);
    }

    public BaseBookDto createPaperBook(PaperBookCreateDto createDto){
        BaseBook paperBookByIsbn = paperBookService.getPaperBookByIsbn(createDto.getIsbn());
        dtoValidatorService.validateIsbn(paperBookByIsbn);
        return paperBookService.createPaperBook(createDto);
    }

    public PaperBookDto extendCreatePaperBook(PaperBookExtendedCreateDto createDto){
        BaseBook paperBookByIsbn = paperBookService.getPaperBookByIsbn(createDto.getIsbn());
        dtoValidatorService.validateIsbn(paperBookByIsbn);
        return paperBookService.extendedCreateAudioBook(createDto);
    }

    public void ratePaperBook(Integer rating, Long paperBookId) {
        dtoValidatorService.checkIfRatingIsCorrect(rating);
        paperBookService.ratePaperBook(rating, paperBookId);
    }

    public void rateAudioBook(Integer rating, Long audiobookId) {
        dtoValidatorService.checkIfRatingIsCorrect(rating);
        audiobookService.rateAudioBook(rating, audiobookId);
    }

    public void rateEBook(Integer rating, Long eBookId) {
        dtoValidatorService.checkIfRatingIsCorrect(rating);
        eBookService.rateEBook(rating, eBookId);
    }

    public void subscribePaperBook(Long bookId, Long userId){
        User user = userService.getUser(userId);
        paperBookService.subscribePaperBook(bookId,user);
    }

    public void unsubscribePaperBook(Long bookId, Long userId){
        User user = userService.getUser(userId);
        paperBookService.unsubscribePaperBook(bookId,user);
    }

    public void subscribeEBook(Long bookId, Long userId){
        User user = userService.getUser(userId);
        eBookService.subscribeEBook(bookId,user);
    }

    public void unsubscribeEBook(Long bookId, Long userId){
        User user = userService.getUser(userId);
        eBookService.unsubscribeEBook(bookId,user);
    }

    public void subscribeAudiobook(Long bookId, Long userId){
        User user = userService.getUser(userId);
        audiobookService.subscribeAudiobook(bookId,user);
    }

    public void unsubscribeAudiobook(Long bookId, Long userId){
        User user = userService.getUser(userId);
        audiobookService.unsubscribeAudiobook(bookId,user);
    }

    public PaperBookDto getPaperBook(Long id) {
        return paperBookService.getPaperBookDto(id);
    }

    public List<BaseBookDto> getPaperBookList() {
        return paperBookService.getPaperBookDtoList();
    }

    public List<PaperBookDto> getPaperBookBestsellerList() {
        return paperBookService.getPaperBookDtoBestsellerList();
    }

    public EBookDto getEBook(Long id) {
        return eBookService.getEBook(id);
    }

    public List<BaseBookDto> getEBookList() {
        return eBookService.getEBookPage();
    }

    public List<EBookDto> getEBookBestsellerList() {
        return eBookService.getEBookBestsellerList();
    }

    public AudiobookDto getAudiobook(Long id) {
        return audiobookService.getAudiobook(id);
    }

    public List<BaseBookDto> getAudiobookList() {
        return audiobookService.getAudiobookList();
    }

    public List<AudiobookDto> getAudiobookBestsellerList() {
        return audiobookService.getAudiobookBestsellerList();
    }

    public byte[] getAudioBookCover(Long id){
        return audiobookService.getBookCover(id);
    }

    public byte[] getPaperBookCover(Long id){
        return paperBookService.getBookCover(id);
    }

    public byte[] getEBookCover(Long id){
        return eBookService.getBookCover(id);
    }

    public void updateAudiobookQuantity(Integer quantity, Long id){
        audiobookService.updateAudiobookQuantity(quantity,id);
    }

    public void updatePaperBookQuantity(Integer quantity, Long id){
        paperBookService.updatePaperBookQuantity(quantity,id);
    }

    public void updateEBookQuantity(Integer quantity, Long id){
        eBookService.updateEbookQuantity(quantity,id);
    }
}
