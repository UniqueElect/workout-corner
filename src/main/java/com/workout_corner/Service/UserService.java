package com.workout_corner.Service;

import com.workout_corner.DTO.UserDto;
import com.workout_corner.Entity.Role;
import com.workout_corner.Entity.User;
import com.workout_corner.Mapper.UserMapper;
import com.workout_corner.Repo.UserRepo;
import com.workout_corner.Request.ChangeRoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
       return users.stream().map(UserMapper::toDTO).toList();
    }

    public UserDto getUserById(Long id) {
       var user = userRepo.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("User not found"));
       return UserMapper.toDTO(user);
    }

    public UserDto changeRole(ChangeRoleRequest changeRoleRequest) {
        User user = userRepo.findById(changeRoleRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (changeRoleRequest.getRole() == null ||
                !EnumSet.allOf(Role.class).contains(changeRoleRequest.getRole())) {
            throw new IllegalArgumentException("Invalid role: " + changeRoleRequest.getRole());
        }
        user.setRole(changeRoleRequest.getRole());
        userRepo.save(user);
        return UserMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepo.deleteById(id);
    }
}
