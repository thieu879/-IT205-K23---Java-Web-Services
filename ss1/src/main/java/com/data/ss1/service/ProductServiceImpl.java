package com.data.ss1.service;

import com.data.ss1.entity.Product;
import com.data.ss1.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public  ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.updateProduct(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
