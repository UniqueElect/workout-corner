package com.workout_corner.Service;

import com.workout_corner.DTO.UserDto;
import com.workout_corner.Entity.User;
import com.workout_corner.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUserService {
    @Autowired
    UserRepo userRepo;

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
