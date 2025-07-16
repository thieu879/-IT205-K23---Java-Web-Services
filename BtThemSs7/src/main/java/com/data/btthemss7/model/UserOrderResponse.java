package com.data.btthemss7.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserOrderResponse {
    private Long orderId;
    private LocalDate date;
    private String fullName;
    private String address;
    private String phoneNumber;
    private List<OrderItemResponse> items;
    private Double totalAmount;
}

