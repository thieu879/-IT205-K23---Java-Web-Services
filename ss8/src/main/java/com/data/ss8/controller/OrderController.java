package com.data.ss8.controller;

import com.data.ss8.model.dto.request.OrderRequestDto;
import com.data.ss8.model.dto.response.ApiResponse;
import com.data.ss8.model.dto.response.OrderResponseDto;
import com.data.ss8.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder(
            @Valid @RequestBody OrderRequestDto orderRequestDto) {

        OrderResponseDto createdOrder = orderService.createOrder(orderRequestDto);

        ApiResponse<OrderResponseDto> response = new ApiResponse<>(
                "Tạo hóa đơn thành công",
                true,
                createdOrder
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();

        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách hóa đơn thành công",
                true,
                orders
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderById(@PathVariable Long id) {
        OrderResponseDto order = orderService.getOrderById(id);

        ApiResponse<OrderResponseDto> response = new ApiResponse<>(
                "Lấy thông tin hóa đơn thành công",
                true,
                order
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getOrdersByCustomerId(
            @PathVariable Long customerId) {
        List<OrderResponseDto> orders = orderService.getOrdersByCustomerId(customerId);

        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách hóa đơn theo khách hàng thành công",
                true,
                orders
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getOrdersByEmployeeId(
            @PathVariable Long employeeId) {
        List<OrderResponseDto> orders = orderService.getOrdersByEmployeeId(employeeId);

        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách hóa đơn theo nhân viên thành công",
                true,
                orders
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<OrderResponseDto> orders = orderService.getOrdersByDateRange(start, end);

        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách hóa đơn theo khoảng thời gian thành công",
                true,
                orders
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/revenue/date-range")
    public ResponseEntity<ApiResponse<Double>> getTotalRevenueByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        Double totalRevenue = orderService.getTotalRevenueByDateRange(start, end);

        ApiResponse<Double> response = new ApiResponse<>(
                "Tính tổng doanh thu theo khoảng thời gian thành công",
                true,
                totalRevenue
        );

        return ResponseEntity.ok(response);
    }
}
