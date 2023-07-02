package com.ecommerce.backend.controller;


import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping("/categories/delete/{id}")
    public void deleteCategoryByID(@PathVariable Long id){
         categoryService.deleteCategoryByID(id);
    }

    @GetMapping("category/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryByID(id);
    }
}
