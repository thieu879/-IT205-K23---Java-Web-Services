package com.data.btthemss7.service;

import com.data.btthemss7.entity.ProductDetail;
import com.data.btthemss7.repository.ProductDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductDetailService {
    private final ProductDetailRepository productDetailRepository;

    public ProductDetailService(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }

    public Optional<ProductDetail> getProductDetailById(Long id) {
        return productDetailRepository.findById(id);
    }

    public List<ProductDetail> getProductDetailsByProductId(Long productId) {
        return productDetailRepository.findByProductId(productId);
    }

    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail updateProductDetail(Long id, ProductDetail productDetailDetails) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));

        productDetail.setYearMaking(productDetailDetails.getYearMaking());
        productDetail.setColor(productDetailDetails.getColor());
        productDetail.setPrice(productDetailDetails.getPrice());
        productDetail.setSize(productDetailDetails.getSize());

        return productDetailRepository.save(productDetail);
    }

    public void deleteProductDetail(Long id) {
        productDetailRepository.deleteById(id);
    }
}
