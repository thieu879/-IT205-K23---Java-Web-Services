package com.data.ss13.entity.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private Integer quantity;
}
