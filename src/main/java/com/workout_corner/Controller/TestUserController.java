package com.workout_corner.Controller;

import com.workout_corner.DTO.UserDto;
import com.workout_corner.Entity.User;
import com.workout_corner.Service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class TestUserController {
    @Autowired
    TestUserService testUserService;
    @PostMapping("/create")
    private User createUser(@RequestBody UserDto userDto){
        return testUserService.createUser(userDto);
    }
    @PostMapping("/delete/{id}")
    private void deleteCart(@PathVariable Long id){
        testUserService.deleteUser(id);
    }
}
