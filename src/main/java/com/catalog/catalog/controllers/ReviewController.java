package com.catalog.catalog.controllers;

import com.catalog.catalog.requests.ReviewRequest;
import com.catalog.catalog.responses.ReviewResponse;
import com.catalog.catalog.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Ендпоінт для отримання відгуків для конкретного товару.
     *
     * @param productId Ідентифікатор товару.
     * @return Список ReviewResponse.
     */
    @GetMapping("/product/{productId}")
    public List<ReviewResponse> getReviewsByProductId(@PathVariable Integer productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    /**
     * Ендпоінт для створення нового відгуку для товару.
     *
     * @param productId     Ідентифікатор товару.
     * @param reviewRequest Дані для створення відгуку.
     * @return Створений ReviewResponse.
     */
    @PostMapping("/product/{productId}")
    public ReviewResponse createReview(@PathVariable Integer productId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.createReview(productId, reviewRequest);
    }
}
