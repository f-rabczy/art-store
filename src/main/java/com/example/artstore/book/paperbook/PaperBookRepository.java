package com.example.artstore.book.paperbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface PaperBookRepository extends JpaRepository<PaperBook,Long> {

    List<PaperBook> findTop5ByOrderBySoldUnitDesc();

    PaperBook findByIsbn(String isbn);

    @Query(value = "select a.imageUrl from PaperBook a where a.id =:id")
    Optional<String> findBookImageUrlUsingId(Long id);
}
