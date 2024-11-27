package com.catalog.catalog.repos;


import com.catalog.catalog.entities.Category;
import com.catalog.catalog.entities.Product;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findTop5ByCategoryOrderByRatingDesc(Category category);
    @Query("SELECT p FROM Product p " +
            "WHERE (:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:categoryId IS NULL OR p.category.categoryId = :categoryId) " +
            "AND (:countryId IS NULL OR p.country.countryId = :countryId) " +
            "ORDER BY p.price ASC")
    List<Product> searchProducts(String name, Integer categoryId, Integer countryId);

}