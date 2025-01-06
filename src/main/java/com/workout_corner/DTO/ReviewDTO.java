package com.workout_corner.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long userId;
    private Long productId;
    private Integer rating;
    private String text;
}
