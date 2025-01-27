package com.workout_corner.DTO;

import com.workout_corner.Entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private Role role;
}
