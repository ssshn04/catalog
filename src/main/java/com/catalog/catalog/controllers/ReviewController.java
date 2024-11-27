package com.catalog.catalog.controllers;

import com.catalog.catalog.entities.Review;
import com.catalog.catalog.requests.ReviewRequest;
import com.catalog.catalog.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {
        Review createdReview = reviewService.createReview(reviewRequest);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
}
