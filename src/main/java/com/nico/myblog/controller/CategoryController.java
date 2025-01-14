package com.nico.myblog.controller;

import com.nico.myblog.dto.CategoryDTO;
import com.nico.myblog.model.Article;
import com.nico.myblog.model.Category;
import com.nico.myblog.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository repository;

    public CategoryController(CategoryRepository categoryRepository) {
        repository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = repository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories.stream().map(CategoryDTO::mapFromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Category category = repository.findById(id).orElse(null);
        if(Objects.isNull(category)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody Category category) {
        Category savedCategory = repository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryDTO(savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = repository.findById(id).orElse(null);
        if(Objects.isNull(category)) {
            return ResponseEntity.notFound().build();
        }
        category.setName(categoryDetails.getName());

        Category updatedCategory = repository.save(category);
        return ResponseEntity.ok(CategoryDTO.mapFromEntity(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category category = repository.findById(id).orElse(null);
        if(Objects.isNull(category)) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(category);
        return ResponseEntity.noContent().build();
    }
}
