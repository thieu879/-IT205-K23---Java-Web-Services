package com.data.ss13.repository;

import com.data.ss13.entity.bt.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID>{
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}