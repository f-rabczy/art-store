package com.example.artstore.book.api.resource;

import com.example.artstore.book.BookFacade;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.PaperBookDto;
import com.example.artstore.book.api.dto.QuantityDto;
import com.example.artstore.book.api.dto.in.RatingDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.AudiobookExtendedCreateDto;
import com.example.artstore.book.api.dto.in.extended.PaperBookExtendedCreateDto;
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
@RequestMapping("/books/paper-books")
public class PaperBookController {

    private final BookFacade bookFacade;


    @GetMapping("/{id}")
    ResponseEntity<PaperBookDto> getPaperBook(@PathVariable Long id){
        return ResponseEntity.ok(bookFacade.getPaperBook(id));
    }

    @GetMapping
    List<BaseBookDto> getPaperBooks() {
        return bookFacade.getPaperBookList();
    }


    @PostMapping
    ResponseEntity<?> createPaperBook(@RequestBody @Valid PaperBookCreateDto createDto){
        return ResponseEntity.ok(bookFacade.createPaperBook(createDto));
    }

    @PostMapping("/extended")
    ResponseEntity<?> extendCreatePaperBook(@RequestBody PaperBookExtendedCreateDto createDto) {
        return ResponseEntity.ok(bookFacade.extendCreatePaperBook(createDto));
    }

    @GetMapping("/bestsellers")
    List<PaperBookDto> getPaperBooksBestsellers() {
        return bookFacade.getPaperBookBestsellerList();
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> ratePaperBook(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        bookFacade.ratePaperBook(ratingDto.getRating(), id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/quantity")
    ResponseEntity<?> changePaperBookQuantity(@PathVariable Long id, @RequestBody QuantityDto quantityDto) {
        bookFacade.updatePaperBookQuantity(quantityDto.getQuantity(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/cover", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getPaperBookCover(@PathVariable Long id) {
        return ResponseEntity.ok(bookFacade.getPaperBookCover(id));
    }

    @PatchMapping("/{bookId}/observers/{userId}")
    ResponseEntity<?> subscribePaperBook(@PathVariable Long bookId, @PathVariable Long userId){
        bookFacade.subscribePaperBook(bookId, userId);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{bookId}/observers/{userId}")
    ResponseEntity<?> unsubscribePaperBook(@PathVariable Long bookId, @PathVariable Long userId){
        bookFacade.unsubscribePaperBook(bookId, userId);
        return ResponseEntity.ok().build();

    }
}
