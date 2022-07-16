package com.example.artstore.book.api.resource;

import com.example.artstore.book.BookFacade;
import com.example.artstore.book.api.dto.AudiobookDto;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.QuantityDto;
import com.example.artstore.book.api.dto.in.RatingDto;
import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.api.dto.in.extended.AudiobookExtendedCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/audiobooks")
public class AudiobookController {

    private final BookFacade bookFacade;

    @PostMapping
    ResponseEntity<?> createBasicAudioBook(@RequestBody AudiobookCreateDto createDto) {
        return ResponseEntity.ok(bookFacade.createAudioBook(createDto));
    }

    @PostMapping("/extended")
    ResponseEntity<?> extendCreateAudioBook(@RequestBody AudiobookExtendedCreateDto createDto) {
        return ResponseEntity.ok(bookFacade.extendCreateAudioBook(createDto));
    }

    @GetMapping(value = "/{id}/cover", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getBookCover(@PathVariable Long id) {
        return ResponseEntity.ok(bookFacade.getAudioBookCover(id));
    }

    @GetMapping("/{id}")
    ResponseEntity<AudiobookDto> getAudiobook(@PathVariable Long id){
        return ResponseEntity.ok(bookFacade.getAudiobook(id));
    }

    @GetMapping
    List<BaseBookDto> getAudiobookList() {
        return bookFacade.getAudiobookList();
    }

    @GetMapping("/bestsellers")
    List<AudiobookDto> getAudiobooksBestsellers() {
        return bookFacade.getAudiobookBestsellerList();
    }

    @PatchMapping("/{id}/rating")
    ResponseEntity<?> rateAudiobook(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        bookFacade.rateAudioBook(ratingDto.getRating(), id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/quantity")
    ResponseEntity<?> changeAudiobookQuantity(@PathVariable Long id, @RequestBody QuantityDto quantityDto) {
        bookFacade.updateAudiobookQuantity(quantityDto.getQuantity(), id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{bookId}/observers/{userId}")
    ResponseEntity<?> subscribeAudiobook(@PathVariable Long bookId, @PathVariable Long userId){
        bookFacade.subscribeAudiobook(bookId, userId);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{bookId}/observers/{userId}")
    ResponseEntity<?> unsubscribeAudiobook(@PathVariable Long bookId, @PathVariable Long userId){
        bookFacade.unsubscribeAudiobook(bookId, userId);
        return ResponseEntity.ok().build();

    }



}
