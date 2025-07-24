package com.data.ss13.service;

import com.data.ss13.entity.bt.Product;
import com.data.ss13.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @Override
    public Product addProduct(Product product){
        return productRepo.save(product);
    }

    @Override
    public Product editProduct(Product product){
        if(productRepo.existsById(product.getId())){
            return productRepo.save(product);
        }
        throw new EntityNotFoundException("Khong ton tai id");
    }

    @Override
    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }
}