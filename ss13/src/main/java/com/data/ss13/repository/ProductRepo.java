package com.data.ss13.repository;

import com.data.ss13.entity.bt.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long>{
}