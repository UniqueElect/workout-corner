package com.workout_corner.Mapper;

import com.workout_corner.DTO.UserDto;
import com.workout_corner.Entity.User;

public class UserMapper {
    public static UserDto toDTO(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
