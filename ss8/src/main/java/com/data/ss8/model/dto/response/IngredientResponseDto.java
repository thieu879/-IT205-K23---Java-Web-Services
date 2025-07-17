package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientResponseDto {
    private Long id;
    private String name;
    private Integer stock;
    private LocalDate expiry;
    private String image;
}
