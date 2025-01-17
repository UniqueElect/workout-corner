package com.workout_corner.Auth;

import com.workout_corner.Config.JwtService;
import com.workout_corner.Repo.UserRepo;
import com.workout_corner.Request.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepo userRepo;
    @GetMapping
    public UserProfileResponse getUserProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", ""); // Убираем Bearer
        var response = new  UserProfileResponse();
        String username = jwtService.extractUsername(token);
        response.setUsername(username);
        response.setRole(userRepo.findRoleByUsername(username));
        return response;
    }
}
