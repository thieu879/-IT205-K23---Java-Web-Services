package com.data.ss6.controller;

import com.data.ss6.entity.Order;
import com.data.ss6.model.DataResponse;
import com.data.ss6.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new DataResponse<>(orders, org.springframework.http.HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Order>> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(new DataResponse<>(createdOrder, org.springframework.http.HttpStatus.CREATED));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<DataResponse<List<Order>>> getOrdersByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Order> orders = orderService.getOrdersByDate(date);
        return ResponseEntity.ok(new DataResponse<>(orders, org.springframework.http.HttpStatus.OK));
    }
}
