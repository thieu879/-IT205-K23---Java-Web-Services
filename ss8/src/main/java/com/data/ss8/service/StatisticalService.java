package com.data.ss8.service;

import com.data.ss8.model.dto.response.*;
import com.data.ss8.repository.OrderDetailRepository;
import com.data.ss8.repository.OrderRepository;
import com.data.ss8.repository.PaymentSlipRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StatisticalService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final PaymentSlipRepository paymentSlipRepository;

    public StatisticalService(OrderDetailRepository orderDetailRepository,
                              OrderRepository orderRepository,
                              PaymentSlipRepository paymentSlipRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.paymentSlipRepository = paymentSlipRepository;
    }

    public List<TopDishDto> getTopDishes() {
        List<Object[]> results = orderDetailRepository.getTopSellingDishes();
        return results.stream()
                .limit(10)
                .map(result -> new TopDishDto(
                        (Long) result[0],           // dishId
                        (String) result[1],         // dishName
                        (Long) result[2],           // totalQuantity
                        (Double) result[3]          // totalRevenue
                ))
                .collect(Collectors.toList());
    }

    public List<TopCustomerDto> getTopCustomers() {
        List<Object[]> results = orderRepository.getTopCustomersBySpending();
        return results.stream()
                .limit(10)
                .map(result -> new TopCustomerDto(
                        (Long) result[0],           // customerId
                        (String) result[1],         // customerName
                        (String) result[2],         // customerEmail
                        (String) result[3],         // customerPhone
                        (Double) result[4],         // totalSpent
                        ((Long) result[5]).intValue() // numberOfOrders
                ))
                .collect(Collectors.toList());
    }

    public Double getCurrentMonthExpenses() {
        LocalDateTime now = LocalDateTime.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        Double expenses = paymentSlipRepository.getCurrentMonthExpenses(currentMonth, currentYear);
        return expenses != null ? expenses : 0.0;
    }

    public List<MonthlyExpenseDto> getMonthlyExpenses(Integer year) {
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }

        List<Object[]> results = paymentSlipRepository.getMonthlyExpensesByYear(year);

        // Tạo danh sách đầy đủ 12 tháng
        List<MonthlyExpenseDto> monthlyExpenses = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            monthlyExpenses.add(new MonthlyExpenseDto(month, year, 0.0));
        }

        // Cập nhật dữ liệu thực tế
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Integer resultYear = (Integer) result[1];
            Double totalExpense = (Double) result[2];

            monthlyExpenses.get(month - 1).setTotalExpense(totalExpense);
        }

        return monthlyExpenses;
    }

    public List<MonthlyRevenueDto> getMonthlyRevenue(Integer year) {
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }

        List<Object[]> results = orderRepository.getMonthlyRevenueByYear(year);

        // Tạo danh sách đầy đủ 12 tháng
        List<MonthlyRevenueDto> monthlyRevenues = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            monthlyRevenues.add(new MonthlyRevenueDto(month, year, 0.0, 0));
        }

        // Cập nhật dữ liệu thực tế
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Integer resultYear = (Integer) result[1];
            Double totalRevenue = (Double) result[2];
            Integer numberOfOrders = ((Long) result[3]).intValue();

            monthlyRevenues.get(month - 1).setTotalRevenue(totalRevenue);
            monthlyRevenues.get(month - 1).setNumberOfOrders(numberOfOrders);
        }

        return monthlyRevenues;
    }

    public TopEmployeeDto getTopEmployee(Integer month, Integer year) {
        if (month == null || year == null) {
            LocalDateTime now = LocalDateTime.now();
            month = now.getMonthValue();
            year = now.getYear();
        }

        List<Object[]> results = orderRepository.getTopEmployeesByRevenueInMonth(month, year);

        if (results.isEmpty()) {
            return null;
        }

        Object[] result = results.get(0);
        return new TopEmployeeDto(
                (Long) result[0],
                (String) result[1],
                (String) result[2],
                (Double) result[3],
                ((Long) result[4]).intValue()
        );
    }
}
