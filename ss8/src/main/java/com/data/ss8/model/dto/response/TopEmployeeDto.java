package com.data.ss8.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopEmployeeDto {
    private Long employeeId;
    private String employeeName;
    private String employeePosition;
    private Double totalRevenue;
    private Integer numberOfOrders;
}
