package com.workout_corner.Controller;

import com.workout_corner.DTO.ReviewDTO;
import com.workout_corner.Entity.Review;
import com.workout_corner.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @PostMapping("/create")
    private Review createReview(@RequestBody ReviewDTO reviewDTO){
        return reviewService.createReview(reviewDTO);
    }
    @GetMapping("/{id}")
    private Review getReviewById(@PathVariable Long id){
        return reviewService.getReviewById(id);
    }
    @GetMapping("/all")
    private List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }
    @GetMapping("/get/{userId}")
    private List<Review> getAllReviewsByUserId(@PathVariable Long userId){
        return reviewService.getAllReviewsByUserId(userId);
    }
    @PostMapping("edit/{id}")
    private Review editReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        return reviewService.editReview(id, reviewDTO);
    }
    @PostMapping("/delete/{id}")
    private void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }
}
