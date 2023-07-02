package com.ecommerce.backend.services;

import com.ecommerce.backend.exception.ResourceNotFound;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategoryByID( Long id){
        categoryRepository.deleteById(id);
    }

    public  Category getCategoryByID(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
}

