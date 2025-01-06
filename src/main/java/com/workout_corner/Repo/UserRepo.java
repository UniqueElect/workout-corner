package com.workout_corner.Repo;

import com.workout_corner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>  {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
