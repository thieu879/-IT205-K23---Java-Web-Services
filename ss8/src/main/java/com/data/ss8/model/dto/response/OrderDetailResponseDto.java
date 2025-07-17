package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Long id;
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private Double priceBuy;
    private Double totalPrice;
}
