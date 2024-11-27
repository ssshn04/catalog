package com.catalog.catalog.controllers;

import com.catalog.catalog.entities.Review;
import com.catalog.catalog.requests.ReviewRequest;
import com.catalog.catalog.responses.ReviewResponse;
import com.catalog.catalog.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {
        Review createdReview = reviewService.createReview(reviewRequest);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsForProduct(@PathVariable Integer productId) {
        List<ReviewResponse> reviews = reviewService.getReviewsForProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
