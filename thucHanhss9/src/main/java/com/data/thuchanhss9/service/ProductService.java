package com.data.thuchanhss9.service;

import com.data.thuchanhss9.dto.ProductDto;
import com.data.thuchanhss9.entity.Product;
import com.data.thuchanhss9.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;
    private CloudinaryService cloudinaryService;
    public ProductService(ProductRepository productRepository, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public ProductDto saveProduct(ProductDto productDto) {
        String imageUrl = null;
        if (productDto.getFile() != null && !productDto.getFile().isEmpty()) {
            try {
                imageUrl = cloudinaryService.uploadFile(productDto.getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageUrl(imageUrl);

        Product savedProduct = productRepository.save(product);
        return convertToResponseDto(savedProduct);
    }
    private ProductDto convertToResponseDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        return dto;
    }

}
