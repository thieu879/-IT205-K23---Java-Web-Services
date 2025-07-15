package com.data.ss6.controller;

import com.data.ss6.entity.Book;
import com.data.ss6.entity.Employee;
import com.data.ss6.model.DataResponse;
import com.data.ss6.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public ResponseEntity<DataResponse<Iterable<Employee>>> getAllEmployees() {
        Iterable<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(new DataResponse<>(employees, HttpStatus.OK));
    }
}
