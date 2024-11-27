package com.catalog.catalog.services;

import com.catalog.catalog.entities.Review;
import com.catalog.catalog.repos.ProductRepository;
import com.catalog.catalog.repos.ReviewRepository;
import com.catalog.catalog.requests.ReviewRequest;
import com.catalog.catalog.responses.ReviewResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    /**
     * Отримання списку відгуків для конкретного товару.
     *
     * @param productId Ідентифікатор товару.
     * @return Список ReviewResponse.
     */
    public List<ReviewResponse> getReviewsByProductId(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Продукт з ID " + productId + " не знайдено.");
        }

        return reviewRepository.findByProduct_ProductId(productId).stream()
                .map(review -> {
                    ReviewResponse response = new ReviewResponse();
                    response.setUserName(review.getUserName());
                    response.setRating(review.getRating());
                    response.setComment(review.getComment());
                    return response;
                })
                .collect(Collectors.toList());
    }

    /**
     * Створення нового відгуку для конкретного товару.
     *
     * @param productId     Ідентифікатор товару.
     * @param reviewRequest Дані для створення відгуку.
     * @return Створений ReviewResponse.
     */
    public ReviewResponse createReview(Integer productId, ReviewRequest reviewRequest) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Продукт з ID " + productId + " не знайдено."));

        Review review = new Review();
        review.setUserName(reviewRequest.getUserName());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setProduct(product);

        Review savedReview = reviewRepository.save(review);

        ReviewResponse response = new ReviewResponse();
        response.setUserName(savedReview.getUserName());
        response.setRating(savedReview.getRating());
        response.setComment(savedReview.getComment());
        return response;
    }
}
