package com.workout_corner.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Long categoryId;
    private Double price;
    private byte[] image;
}
