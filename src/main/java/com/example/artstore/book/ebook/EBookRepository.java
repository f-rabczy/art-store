package com.example.artstore.book.ebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface EBookRepository extends JpaRepository<EBook,Long> {

    List<EBook> findTop5ByOrderBySoldUnitDesc();

    EBook findByIsbn(String isbn);

    @Query(value = "select a.imageUrl from EBook a where a.id =:id")
    Optional<String> findBookImageUrlUsingId(Long id);
}
