package com.data.ss6.controller;

import com.data.ss6.entity.Book;
import com.data.ss6.entity.Employee;
import com.data.ss6.model.DataResponse;
import com.data.ss6.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Employee>> getEmployeeById(Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            return ResponseEntity.ok(new DataResponse<>(employee, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }
    @PostMapping
    public ResponseEntity<DataResponse<Employee>> createEmployee(Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(savedEmployee, HttpStatus.CREATED));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmployee = employeeService.update(employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(new DataResponse<>(updatedEmployee, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
    }
}
