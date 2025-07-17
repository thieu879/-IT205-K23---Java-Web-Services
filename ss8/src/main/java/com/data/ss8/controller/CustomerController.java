package com.data.ss8.controller;

import com.data.ss8.model.dto.request.CustomerRequestDto;
import com.data.ss8.model.dto.response.ApiResponse;
import com.data.ss8.model.dto.response.CustomerResponseDto;
import com.data.ss8.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDto>> createCustomer(
            @Valid @RequestBody CustomerRequestDto customerRequestDto) {

        CustomerResponseDto createdCustomer = customerService.createCustomer(customerRequestDto);

        ApiResponse<CustomerResponseDto> response = new ApiResponse<>(
                "Thêm khách hàng thành công",
                true,
                createdCustomer
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto customerRequestDto) {

        CustomerResponseDto updatedCustomer = customerService.updateCustomer(id, customerRequestDto);

        ApiResponse<CustomerResponseDto> response = new ApiResponse<>(
                "Cập nhật khách hàng thành công",
                true,
                updatedCustomer
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "Xóa khách hàng thành công",
                true,
                null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getAllCustomers() {
        List<CustomerResponseDto> customers = customerService.getAllCustomers();

        ApiResponse<List<CustomerResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách khách hàng thành công",
                true,
                customers
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto customer = customerService.getCustomerById(id);

        ApiResponse<CustomerResponseDto> response = new ApiResponse<>(
                "Lấy thông tin khách hàng thành công",
                true,
                customer
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getCustomersByStatus(
            @PathVariable Boolean status) {
        List<CustomerResponseDto> customers = customerService.getCustomersByStatus(status);

        ApiResponse<List<CustomerResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách khách hàng theo trạng thái thành công",
                true,
                customers
        );

        return ResponseEntity.ok(response);
    }
}
