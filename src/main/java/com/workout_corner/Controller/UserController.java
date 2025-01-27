package com.workout_corner.Controller;

import com.workout_corner.DTO.UserDto;
import com.workout_corner.Request.ChangeRoleRequest;
import com.workout_corner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    private UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @GetMapping("/all")
    private List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/role")
    private UserDto changeRole(@RequestBody ChangeRoleRequest changeRoleRequest ){
        return userService.changeRole(changeRoleRequest);
    }
    @PostMapping("/delete/{id}")
    private void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
