package com.data.ss6.service;

import com.data.ss6.entity.ProductCart;
import com.data.ss6.entity.User;
import com.data.ss6.repository.ProductCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductCartService {

    private final ProductCartRepository productCartRepository;

    public ProductCartService(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    // Thêm method để lấy tất cả ProductCart
    public List<ProductCart> getAllProductCarts() {
        return productCartRepository.findAll();
    }

    public List<ProductCart> getCartItemsByUser(User user) {
        return productCartRepository.findAllByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart productCart = productCartRepository.findById(id).orElse(null);
        if (productCart != null) {
            productCart.setQuantity(quantity);
            return productCartRepository.save(productCart);
        }
        return null;
    }

    public void removeFromCart(Long id) {
        productCartRepository.deleteById(id);
    }
}
