package com.data.ss8.controller;

import com.data.ss8.model.dto.request.EmployeeRequestDto;
import com.data.ss8.model.dto.response.ApiResponse;
import com.data.ss8.model.dto.response.EmployeeResponseDto;
import com.data.ss8.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> createEmployee(
            @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {

        EmployeeResponseDto createdEmployee = employeeService.createEmployee(employeeRequestDto);

        ApiResponse<EmployeeResponseDto> response = new ApiResponse<>(
                "Thêm nhân viên thành công",
                true,
                createdEmployee
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {

        EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, employeeRequestDto);

        ApiResponse<EmployeeResponseDto> response = new ApiResponse<>(
                "Cập nhật nhân viên thành công",
                true,
                updatedEmployee
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "Xóa nhân viên thành công",
                true,
                null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees();

        ApiResponse<List<EmployeeResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách nhân viên thành công",
                true,
                employees
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDto employee = employeeService.getEmployeeById(id);

        ApiResponse<EmployeeResponseDto> response = new ApiResponse<>(
                "Lấy thông tin nhân viên thành công",
                true,
                employee
        );

        return ResponseEntity.ok(response);
    }
}
