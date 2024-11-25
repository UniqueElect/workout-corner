package com.workout_corner.DTO;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
}
