package com.example.artstore.book.basebook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReviewCaretakerRepository extends JpaRepository<BookReviewCaretaker,Long> {

    Optional<BookReviewCaretaker> findByBookReview_Id(Long aLong);
}
