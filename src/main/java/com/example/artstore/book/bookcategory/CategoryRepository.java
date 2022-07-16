package com.example.artstore.book.bookcategory;

import org.springframework.data.jpa.repository.JpaRepository;

interface CategoryRepository extends JpaRepository<BookCategory,Long> {
}
