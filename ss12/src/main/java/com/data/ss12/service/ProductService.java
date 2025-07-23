package com.data.ss12.service;

import com.data.ss12.model.dto.request.ProductRequestDTO;
import com.data.ss12.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product create(ProductRequestDTO dto);
    Product update(Long id, ProductRequestDTO dto);
    void delete(Long id);
}