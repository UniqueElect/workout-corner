package com.workout_corner.Mapper;

import com.workout_corner.DTO.ProductDTO;
import com.workout_corner.Entity.Product;
import com.workout_corner.Entity.Category;

public class ProductMapper {
    public static Product toEntity(ProductDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        product.setImage(dto.getImage());
        return product;
    }

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategoryId(product.getCategory().getId());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());
        return dto;
    }
}

