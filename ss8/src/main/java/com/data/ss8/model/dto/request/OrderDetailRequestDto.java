package com.data.ss8.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequestDto {
    @NotNull(message = "ID món ăn không được để trống")
    private Long dishId;

    @NotNull(message = "Số lượng không được để trống")
    @Positive(message = "Số lượng phải là số dương")
    private Integer quantity;

    @NotNull(message = "Giá mua không được để trống")
    @Positive(message = "Giá mua phải là số dương")
    private Double priceBuy;
}
