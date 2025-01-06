package com.workout_corner.Service;

import com.workout_corner.DTO.ReviewDTO;
import com.workout_corner.Entity.Review;
import com.workout_corner.Repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepo reviewRepo;
    public void validateRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }

    public Review createReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        validateRating(reviewDTO.getRating());
        review.setUserId(reviewDTO.getUserId());
        review.setProductId(reviewDTO.getProductId());
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getText());
        review.setCreatedAt(Instant.now());
        return reviewRepo.save(review);
    }

    public Review getReviewById(Long id) {
        return reviewRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }
    public List<Review> getAllReviewsByUserId(Long userId) {
        return reviewRepo.findByUserId(userId);
    }

    public Review editReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        validateRating(reviewDTO.getRating());
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getText());
        return reviewRepo.save(review);
    }


    public void deleteReview(Long id) {
        if (!reviewRepo.existsById(id)) {
            throw new IllegalArgumentException("Review not found");
        }
        reviewRepo.deleteById(id);
    }
}
