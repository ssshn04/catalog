package com.catalog.catalog.services;

import com.catalog.catalog.requests.ReviewRequest;
import com.catalog.catalog.entities.Product;
import com.catalog.catalog.entities.Review;
import com.catalog.catalog.repos.ProductRepository;
import com.catalog.catalog.repos.ReviewRepository;
import com.catalog.catalog.responses.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;


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
    public List<ReviewResponse> getReviewsForProduct(Integer productId) {
        List<Review> reviews = reviewRepository.findByProductProductId(productId);
        return reviews.stream()
                .map(review -> {
                    ReviewResponse response = new ReviewResponse();
                    response.setUserName(review.getUserName());
                    response.setRating(review.getRating());
                    response.setComment(review.getComment());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
