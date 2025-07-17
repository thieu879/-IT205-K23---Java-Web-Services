package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopDishDto {
    private Long dishId;
    private String dishName;
    private Long totalQuantity;
    private Double totalRevenue;
}

