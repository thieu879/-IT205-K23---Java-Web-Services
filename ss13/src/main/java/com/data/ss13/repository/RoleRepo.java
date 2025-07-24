package com.data.ss13.repository;

import com.data.ss13.entity.bt.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
