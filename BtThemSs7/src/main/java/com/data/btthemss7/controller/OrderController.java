package com.data.btthemss7.controller;
import com.data.btthemss7.entity.Order;
import com.data.btthemss7.model.DataResponse;
import com.data.btthemss7.model.UserOrderResponse;
import com.data.btthemss7.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<DataResponse<Order>> createOrder(@RequestBody com.data.btthemss7.model.CreateOrderRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(new DataResponse<>(order));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DataResponse<List<UserOrderResponse>>> getUserOrders(@PathVariable Long userId) {
        List<UserOrderResponse> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(new DataResponse<>(orders));
    }
}
