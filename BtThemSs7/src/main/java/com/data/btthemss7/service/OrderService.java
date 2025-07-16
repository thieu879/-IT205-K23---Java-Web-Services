package com.data.btthemss7.service;

import com.data.btthemss7.entity.*;
import com.data.btthemss7.model.CreateOrderRequest;
import com.data.btthemss7.model.OrderItemRequest;
import com.data.btthemss7.model.OrderItemResponse;
import com.data.btthemss7.model.UserOrderResponse;
import com.data.btthemss7.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductDetailRepository productDetailRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        UserRepository userRepository,
                        ProductDetailRepository productDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public Order createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tạo order
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setUser(user);
        order.setFullName(request.getFullName());
        order.setAddress(request.getAddress());
        order.setPhoneNumber(request.getPhoneNumber());

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequest item : request.getItems()) {
            ProductDetail productDetail = productDetailRepository.findById(item.getProductDetailId())
                    .orElseThrow(() -> new RuntimeException("ProductDetail not found"));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setProductDetail(productDetail);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrice(productDetail.getPrice());

            orderDetailRepository.save(orderDetail);
        }

        return savedOrder;
    }

    public List<UserOrderResponse> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserIdOrderByDateDesc(userId);

        return orders.stream().map(this::convertToUserOrderResponse).collect(Collectors.toList());
    }

    private UserOrderResponse convertToUserOrderResponse(Order order) {
        UserOrderResponse response = new UserOrderResponse();
        response.setOrderId(order.getId());
        response.setDate(order.getDate());
        response.setFullName(order.getFullName());
        response.setAddress(order.getAddress());
        response.setPhoneNumber(order.getPhoneNumber());

        List<OrderItemResponse> items = order.getOrderDetails().stream()
                .map(detail -> {
                    OrderItemResponse item = new OrderItemResponse();
                    item.setProductName(detail.getProductDetail().getProduct().getName());
                    item.setColor(detail.getProductDetail().getColor());
                    item.setSize(detail.getProductDetail().getSize());
                    item.setQuantity(detail.getQuantity());
                    item.setPrice(detail.getPrice());
                    item.setTotalPrice(detail.getPrice() * detail.getQuantity());
                    return item;
                }).collect(Collectors.toList());

        response.setItems(items);
        response.setTotalAmount(items.stream().mapToDouble(OrderItemResponse::getTotalPrice).sum());

        return response;
    }
}
