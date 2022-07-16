package com.example.artstore.book.api.resource;

import com.example.artstore.book.BookFacade;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.EBookDto;
import com.example.artstore.book.api.dto.QuantityDto;
import com.example.artstore.book.api.dto.in.RatingDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.AudiobookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.EBookExtendedCreateDto;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/e-books")
public class EBookController {

    private final BookFacade bookFacade;

    @GetMapping("/{id}")
    ResponseEntity<EBookDto> getEBook(@PathVariable Long id){
        return ResponseEntity.ok(bookFacade.getEBook(id));
    }

    @GetMapping
    List<BaseBookDto> getEBooks() {
        return bookFacade.getEBookList();
    }


    @GetMapping("/bestsellers")
    List<EBookDto> getEBooksBestsellers() {
        return bookFacade.getEBookBestsellerList();
    }


    @PatchMapping("/{id}")
    ResponseEntity<?> rateEBook(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        bookFacade.rateEBook(ratingDto.getRating(), id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> createBasicEBook(@RequestBody @Valid EBookCreateDto createDto){
        return ResponseEntity.ok(bookFacade.createEBook(createDto));
    }

    @PostMapping("/extended")
    ResponseEntity<?> extendCreateEBook(@RequestBody EBookExtendedCreateDto createDto) {
        return ResponseEntity.ok(bookFacade.extendCreateEBook(createDto));
    }

    @PatchMapping("/{id}/quantity")
    ResponseEntity<?> changeEbookQuantity(@PathVariable Long id, @RequestBody QuantityDto quantityDto) {
        bookFacade.updateEBookQuantity(quantityDto.getQuantity(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/cover", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getEBookCover(@PathVariable Long id) {
        return ResponseEntity.ok(bookFacade.getEBookCover(id));
    }

    @PatchMapping("/{bookId}/observers/{userId}")
    ResponseEntity<?> subscribeEBook(@PathVariable Long bookId, @PathVariable Long userId){
        bookFacade.subscribeEBook(bookId, userId);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{bookId}/observers/{userId}")
    ResponseEntity<?> unsubscribeEBook(@PathVariable Long bookId, @PathVariable Long userId){
        bookFacade.unsubscribeEBook(bookId, userId);
        return ResponseEntity.ok().build();

    }

}
