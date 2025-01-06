package com.workout_corner.Repo;

import com.workout_corner.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
}
