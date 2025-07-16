package com.data.btthemss7.model;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productDetailId;
    private Integer quantity;
}

