package com.data.ss13.repository;

import com.data.ss13.entity.bt.Product;
import com.data.ss13.entity.bt.ProductCart;
import com.data.ss13.entity.bt.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCartRepo extends JpaRepository<ProductCart, Long>{
    List<ProductCart> findAllByUserId(UUID userId);
    Optional<ProductCart> findByUserAndProduct(User user, Product product);
    Optional<ProductCart> findByUserIdAndProductId(UUID userId, Long productId);
    void deleteByUserIdAndProductId(UUID userId, Long productId);
}