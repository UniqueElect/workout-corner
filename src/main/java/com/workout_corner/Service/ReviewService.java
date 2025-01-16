package com.workout_corner.Service;

import com.workout_corner.DTO.ReviewDTO;
import com.workout_corner.Entity.Product;
import com.workout_corner.Entity.Review;
import com.workout_corner.Repo.ProductRepo;
import com.workout_corner.Repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    ProductRepo productRepo;
    public void validateRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }

    public Review createReview(ReviewDTO reviewDTO) {
        var productId = reviewDTO.getProductId();
        var product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        validateRating(reviewDTO.getRating());

        Review review = new Review();
        review.setUserId(reviewDTO.getUserId());
        review.setProductId(productId);
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getText());
        review.setCreatedAt(Instant.now());

        double avg = product.getAverageRating();
        int count = product.getReviewCount();
        avg = ((count * avg) + reviewDTO.getRating()) / (count + 1);
        product.setAverageRating(Math.round(avg * 10.0) / 10.0);
        product.setReviewCount(count + 1);
        productRepo.save(product);

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
        Review review = reviewRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        Long productId = review.getProductId();
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        reviewRepo.deleteById(id);
        int count = product.getReviewCount();
        if (count == 1) {
            product.setAverageRating(0.0);
            product.setReviewCount(0);
        } else {
            double avg = product.getAverageRating();
            avg = ((count * avg) - review.getRating()) / (count - 1);
            product.setAverageRating(Math.round(avg * 10.0) / 10.0);
            product.setReviewCount(count - 1);
        }
        productRepo.save(product);
    }
}
