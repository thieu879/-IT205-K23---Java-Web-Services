package com.data.btthemss7.controller;

import com.data.btthemss7.entity.Product;
import com.data.btthemss7.model.DataResponse;
import com.data.btthemss7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        DataResponse<List<Product>> response = new DataResponse<>(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(new DataResponse<>(product.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        DataResponse<Product> response = new DataResponse<>(createdProduct);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(new DataResponse<>(updatedProduct));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        DataResponse<String> response = new DataResponse<>("Product deleted successfully");
        return ResponseEntity.ok(response);
    }
}
