package com.catalog.catalog.services;

import com.catalog.catalog.requests.ReviewRequest;
import com.catalog.catalog.entities.Product;
import com.catalog.catalog.entities.Review;
import com.catalog.catalog.repos.ProductRepository;
import com.catalog.catalog.repos.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Review createReview(ReviewRequest reviewRequest) {
        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Review review = new Review();
        review.setUserName(reviewRequest.getUserName());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setProduct(product);

        return reviewRepository.save(review);
    }
}
