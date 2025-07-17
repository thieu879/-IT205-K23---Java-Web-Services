package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private Integer numberOfPayments;
    private Boolean status;
    private LocalDateTime createdAt;
}
