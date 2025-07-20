package com.data.thuchanhss9.controller;

import com.data.thuchanhss9.dto.DataResponse;
import com.data.thuchanhss9.dto.ProductDto;
import com.data.thuchanhss9.entity.Product;
import com.data.thuchanhss9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(new DataResponse<>(products, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductDto>> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.saveProduct(productDto);
        return new ResponseEntity<>(new DataResponse<>(savedProduct, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
