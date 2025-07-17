package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopCustomerDto {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Double totalSpent;
    private Integer numberOfOrders;
}

