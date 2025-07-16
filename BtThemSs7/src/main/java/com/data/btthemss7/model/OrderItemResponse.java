package com.data.btthemss7.model;

import lombok.Data;

@Data
public class OrderItemResponse {
    private String productName;
    private String color;
    private String size;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
}
