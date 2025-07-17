package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSlipResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double money;
    private LocalDateTime createdAt;
}
