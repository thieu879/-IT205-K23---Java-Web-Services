package com.data.ss6.controller;

import com.data.ss6.entity.Product;
import com.data.ss6.model.DataResponse;
import com.data.ss6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts(Pageable pageable) {
        List<Product> products = productService.getAllProducts(pageable, null);
        return ResponseEntity.ok(new DataResponse<>(products, org.springframework.http.HttpStatus.OK));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(new DataResponse<>(product, org.springframework.http.HttpStatus.OK));
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, org.springframework.http.HttpStatus.NOT_FOUND));
        }
    }
    @PostMapping
    public ResponseEntity<DataResponse<Product>> createProduct(Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(new DataResponse<>(createdProduct, org.springframework.http.HttpStatus.CREATED));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(new DataResponse<>(updatedProduct, org.springframework.http.HttpStatus.OK));
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, org.springframework.http.HttpStatus.NOT_FOUND));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok(new DataResponse<>(null, org.springframework.http.HttpStatus.OK));
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, org.springframework.http.HttpStatus.NOT_FOUND));
        }
    }
}
