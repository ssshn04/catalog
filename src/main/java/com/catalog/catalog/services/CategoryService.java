package com.catalog.catalog.services;

import com.catalog.catalog.entities.Category;
import com.catalog.catalog.responses.CategoryResponse;
import com.catalog.catalog.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        System.out.println(categories.getFirst().getCategoryId());
        return categories.stream()
                .map(category -> {
                    CategoryResponse response = new CategoryResponse();
                    response.setName(category.getName());
                    response.setCategoryId(category.getCategoryId());
                    return response;
                })
                .collect(Collectors.toList());
    }
}