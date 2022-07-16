package com.example.artstore.user.api.resource;


import com.example.artstore.book.api.dto.ReviewDto;
import com.example.artstore.book.api.dto.in.ReviewInDto;
import com.example.artstore.user.domain.UserService;
import com.example.artstore.user.api.dto.CartBookDto;
import com.example.artstore.user.api.dto.UserCreateDto;
import com.example.artstore.user.api.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController {

    private final UserService userService;

    @PostMapping("/{id}/review")
    ResponseEntity<ReviewDto> addReview(@PathVariable Long id, @RequestBody ReviewInDto review) {
        return ResponseEntity.ok(userService.addReview(id, review));

    }

    @PutMapping("/{userId}/review/{reviewId}")
    ResponseEntity<ReviewDto> editReview(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody ReviewInDto review) {
        return ResponseEntity.ok(userService.editReview(userId, reviewId,review));
    }

    @PatchMapping("/{userId}/review/{reviewId}")
    ResponseEntity<ReviewDto> restoreReview(@PathVariable Long userId, @PathVariable Long reviewId) {
        return ResponseEntity.ok(userService.restoreReview(userId, reviewId));
    }

    @GetMapping("/{userId}/review/{reviewId}")
    ResponseEntity<ReviewDto> getReview(@PathVariable Long userId, @PathVariable Long reviewId) {
        return ResponseEntity.ok(userService.getReview(userId, reviewId));
    }


    @PostMapping
    ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateDto request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/{id}/carts")
    ResponseEntity<?> addBooksToCart(@RequestBody List<CartBookDto> cartBookDtoList, @PathVariable Long id) {
        userService.addBooksToUserCart(cartBookDtoList, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/carts")
    ResponseEntity<?> addBooksToCart(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserCart(id));
    }

    @GetMapping("/{id}/notifciations")
    ResponseEntity<?> getNotifications(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserNotifications(id));
    }
}
