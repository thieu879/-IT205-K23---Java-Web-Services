package com.data.ss8.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotNull(message = "ID khách hàng không được để trống")
    private Long customerId;

    @NotNull(message = "ID nhân viên không được để trống")
    private Long employeeId;

    @NotEmpty(message = "Danh sách chi tiết hóa đơn không được để trống")
    @Valid
    private List<OrderDetailRequestDto> orderDetails;
}
