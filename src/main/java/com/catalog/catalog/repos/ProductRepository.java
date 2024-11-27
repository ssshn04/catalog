package com.catalog.catalog.repos;


import com.catalog.catalog.entities.Category;
import com.catalog.catalog.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findTop5ByCategoryOrderByRatingDesc(Category category);
}