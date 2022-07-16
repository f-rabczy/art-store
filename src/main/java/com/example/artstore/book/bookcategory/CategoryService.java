package com.example.artstore.book.bookcategory;

import com.example.artstore.book.api.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(BookCategory::getCategory)
                .map(CategoryType::name)
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
