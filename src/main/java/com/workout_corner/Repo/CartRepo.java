package com.workout_corner.Repo;

import com.workout_corner.Entity.Cart;
import com.workout_corner.Entity.Product;
import com.workout_corner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
