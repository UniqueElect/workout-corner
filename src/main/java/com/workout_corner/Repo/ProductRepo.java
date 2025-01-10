package com.workout_corner.Repo;

import com.workout_corner.Entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max AND (:categoryId IS NULL OR p.category.id = :categoryId)")
    List<Product> findAllByPriceBetweenAndCategoryId(Long min, Long max, Long categoryId, Sort sort);

}
