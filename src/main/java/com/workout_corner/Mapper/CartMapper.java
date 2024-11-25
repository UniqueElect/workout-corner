package com.workout_corner.Mapper;

import com.workout_corner.DTO.CartDTO;
import com.workout_corner.DTO.CartItemDTO;
import com.workout_corner.Entity.Cart;
import com.workout_corner.Entity.CartItem;

import java.util.stream.Collectors;

public class CartMapper {
    public CartDTO convertToDto(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setItems(cart.getItems().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public CartItemDTO convertToDto(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}
