package com.data.ss1.service;

import com.data.ss1.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
