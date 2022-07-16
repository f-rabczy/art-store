package com.example.artstore.author.api.resource;

import com.example.artstore.author.AuthorService;
import com.example.artstore.author.api.dto.AuthorCreateDto;
import com.example.artstore.author.api.dto.AuthorWithBooksCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    ResponseEntity<?> createAuthorWithoutBooks(@RequestBody AuthorCreateDto createDto){
        return ResponseEntity.ok(authorService.createAuthorWithoutBooks(createDto));
    }

    @PostMapping("/books")
    ResponseEntity<?> createAuthorWithBooks(@RequestBody AuthorWithBooksCreateDto createDto){
        return ResponseEntity.ok(authorService.createAuthorWithBooks(createDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getAuthor(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorWithBooks(id));
    }
}
