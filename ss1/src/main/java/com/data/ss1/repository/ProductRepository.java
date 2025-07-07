package com.data.ss1.repository;

import com.data.ss1.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
