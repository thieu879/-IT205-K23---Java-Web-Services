package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String status;
    private String image;
}
