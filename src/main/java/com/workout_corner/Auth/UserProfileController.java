package com.workout_corner.Auth;

import com.workout_corner.Config.JwtService;
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
    @GetMapping
    public String getUserProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", ""); // Убираем Bearer
        String username = jwtService.extractUsername(token);
        return username;
    }
}
