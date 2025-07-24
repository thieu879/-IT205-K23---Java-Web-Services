package com.data.ss13.service;

import com.data.ss13.entity.bt.Product;
import com.data.ss13.entity.bt.ProductCart;
import com.data.ss13.entity.bt.User;
import com.data.ss13.entity.dto.request.CartRequest;
import com.data.ss13.repository.ProductCartRepo;
import com.data.ss13.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {

    private final ProductCartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserService userService;

    @Override
    public void addToCart(CartRequest request) {
        User user = userService.getCurrentUser();
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductCart cart = cartRepo.findByUserAndProduct(user, product).orElse(null);
        if (cart == null) {
            cart = ProductCart.builder()
                    .user(user)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
        } else {
            cart.setQuantity(cart.getQuantity() + request.getQuantity());
        }
        cartRepo.save(cart);
    }

    @Override
    public void removeFromCart(Long productId) {
        User user = userService.getCurrentUser();
        cartRepo.deleteByUserIdAndProductId(user.getId(), productId);
    }

    @Override
    public void updateQuantity(Long productId, Integer quantity) {
        User user = userService.getCurrentUser();
        ProductCart cart = cartRepo.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        cartRepo.save(cart);
    }

    @Override
    public List<ProductCartDTO> getUserCart() {
        User user = userService.getCurrentUser();
        return cartRepo.findAllByUserId(user.getId()).stream()
                .map(ProductCartDTO::fromEntity)
                .collect(Collectors.toList());
    }
}