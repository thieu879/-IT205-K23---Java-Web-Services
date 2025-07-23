package com.data.ss12.repository;
import com.data.ss12.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long>{
}
