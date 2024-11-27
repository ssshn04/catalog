package com.catalog.catalog.repos;

import com.catalog.catalog.entities.Review;
import com.catalog.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(Long productId);
}