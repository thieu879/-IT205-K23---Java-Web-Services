package com.data.btthemss7.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long userId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private List<OrderItemRequest> items;
}

