package com.example.artstore.book.audiobook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface AudiobookRepository extends JpaRepository<Audiobook,Long> {

    List<Audiobook> findTop5ByOrderBySoldUnitDesc();

    Audiobook findByIsbn(String isbn);

    @Query(value = "select a.imageUrl from Audiobook a where a.id =:id")
    Optional<String> findBookImageUrlUsingId(Long id);
}
