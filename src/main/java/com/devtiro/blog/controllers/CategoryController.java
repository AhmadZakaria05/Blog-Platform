package com.devtiro.blog.controllers;

import com.devtiro.blog.domain.dto.CategoryDto;
import com.devtiro.blog.domain.dto.CreateCategoryRequest;
import com.devtiro.blog.domain.entity.Category;
import com.devtiro.blog.mappers.CategoryMapper;
import com.devtiro.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categoryDto = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        Category category = categoryMapper.toEntity(createCategoryRequest);
        Category saveCategory = categoryService.createCategory(category);

        return new  ResponseEntity<>(
                categoryMapper.toDto(saveCategory),
                HttpStatus.CREATED
                );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
