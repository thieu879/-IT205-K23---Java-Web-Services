package com.data.ss6.repository;

import com.data.ss6.entity.ProductCart;
import com.data.ss6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findAllByUser(User user);
}
