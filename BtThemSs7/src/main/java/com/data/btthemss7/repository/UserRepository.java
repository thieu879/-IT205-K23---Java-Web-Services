package com.data.btthemss7.repository;

import com.data.btthemss7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u " +
            "JOIN u.orders o " +
            "JOIN o.orderDetails od " +
            "GROUP BY u.id " +
            "ORDER BY SUM(od.quantity) DESC " +
            "LIMIT 3")
    List<User> findTop3UsersByOrderQuantity();
}
