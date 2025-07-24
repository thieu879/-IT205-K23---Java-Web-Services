package com.data.ss13.service;

import com.data.ss13.entity.bt.*;
import com.data.ss13.entity.dto.request.CheckoutRequest;
import com.data.ss13.repository.OrderDetailRepo;
import com.data.ss13.repository.OrderRepo;
import com.data.ss13.repository.ProductCartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ProductCartRepo cartRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final UserService userService;

    @Override
    public void checkout(CheckoutRequest request) {
        User user = userService.getCurrentUser();
        List<ProductCart> cartItems = cartRepo.findAllByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        double total = cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();

        Order order = Order.builder()
                .receiver(request.getReceiver())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .totalMoney(total)
                .user(user)
                .build();
        orderRepo.save(order);

        for (ProductCart cartItem : cartItems) {
            OrderDetail detail = OrderDetail.builder()
                    .order(order)
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .priceBuy(cartItem.getProduct().getPrice())
                    .build();
            orderDetailRepo.save(detail);
        }

        cartRepo.deleteAll(cartItems);
    }
}