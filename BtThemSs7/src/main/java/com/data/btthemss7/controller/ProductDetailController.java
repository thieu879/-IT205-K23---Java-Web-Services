package com.data.btthemss7.controller;

import com.data.btthemss7.entity.ProductDetail;
import com.data.btthemss7.model.DataResponse;
import com.data.btthemss7.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productDetails")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<DataResponse<List<ProductDetail>>> getAllProductDetails() {
        List<ProductDetail> productDetails = productDetailService.getAllProductDetails();
        return ResponseEntity.ok(new DataResponse<>(productDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ProductDetail>> getProductDetailById(@PathVariable Long id) {
        Optional<ProductDetail> productDetail = productDetailService.getProductDetailById(id);
        if (productDetail.isPresent()) {
            return ResponseEntity.ok(new DataResponse<>(productDetail.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<DataResponse<List<ProductDetail>>> getProductDetailsByProductId(@PathVariable Long productId) {
        List<ProductDetail> productDetails = productDetailService.getProductDetailsByProductId(productId);
        return ResponseEntity.ok(new DataResponse<>(productDetails));
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductDetail>> createProductDetail(@RequestBody ProductDetail productDetail) {
        ProductDetail createdProductDetail = productDetailService.createProductDetail(productDetail);
        return ResponseEntity.ok(new DataResponse<>(createdProductDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ProductDetail>> updateProductDetail(@PathVariable Long id, @RequestBody ProductDetail productDetail) {
        try {
            ProductDetail updatedProductDetail = productDetailService.updateProductDetail(id, productDetail);
            return ResponseEntity.ok(new DataResponse<>(updatedProductDetail));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteProductDetail(@PathVariable Long id) {
        productDetailService.deleteProductDetail(id);
        return ResponseEntity.ok(new DataResponse<>("ProductDetail deleted successfully"));
    }
}
