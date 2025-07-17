package com.data.ss8.controller;

import com.data.ss8.model.dto.response.*;
import com.data.ss8.service.StatisticalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticalController {

    private final StatisticalService statisticalService;

    public StatisticalController(StatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }

    @GetMapping("/top-dishes")
    public ResponseEntity<ApiResponse<List<TopDishDto>>> getTopDishes() {
        List<TopDishDto> topDishes = statisticalService.getTopDishes();

        ApiResponse<List<TopDishDto>> response = new ApiResponse<>(
                "Lấy danh sách top 10 món ăn bán chạy nhất thành công",
                true,
                topDishes
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-customers")
    public ResponseEntity<ApiResponse<List<TopCustomerDto>>> getTopCustomers() {
        List<TopCustomerDto> topCustomers = statisticalService.getTopCustomers();

        ApiResponse<List<TopCustomerDto>> response = new ApiResponse<>(
                "Lấy danh sách top 10 khách hàng tiêu nhiều tiền nhất thành công",
                true,
                topCustomers
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-month-expenses")
    public ResponseEntity<ApiResponse<Double>> getCurrentMonthExpenses() {
        Double currentMonthExpenses = statisticalService.getCurrentMonthExpenses();

        ApiResponse<Double> response = new ApiResponse<>(
                "Lấy tổng số tiền chi cho tháng hiện tại thành công",
                true,
                currentMonthExpenses
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/monthly-expenses")
    public ResponseEntity<ApiResponse<List<MonthlyExpenseDto>>> getMonthlyExpenses(
            @RequestParam(required = false) Integer year) {
        List<MonthlyExpenseDto> monthlyExpenses = statisticalService.getMonthlyExpenses(year);

        ApiResponse<List<MonthlyExpenseDto>> response = new ApiResponse<>(
                "Lấy số tiền chi tiêu theo tháng thành công",
                true,
                monthlyExpenses
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<ApiResponse<List<MonthlyRevenueDto>>> getMonthlyRevenue(
            @RequestParam(required = false) Integer year) {
        List<MonthlyRevenueDto> monthlyRevenue = statisticalService.getMonthlyRevenue(year);

        ApiResponse<List<MonthlyRevenueDto>> response = new ApiResponse<>(
                "Lấy doanh thu theo tháng thành công",
                true,
                monthlyRevenue
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-employee")
    public ResponseEntity<ApiResponse<TopEmployeeDto>> getTopEmployee(
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {
        TopEmployeeDto topEmployee = statisticalService.getTopEmployee(month, year);

        if (topEmployee == null) {
            ApiResponse<TopEmployeeDto> response = new ApiResponse<>(
                    "Không có dữ liệu nhân viên cho tháng được chọn",
                    false,
                    null
            );
            return ResponseEntity.ok(response);
        }

        ApiResponse<TopEmployeeDto> response = new ApiResponse<>(
                "Lấy nhân viên có doanh thu cao nhất trong tháng thành công",
                true,
                topEmployee
        );

        return ResponseEntity.ok(response);
    }
}

