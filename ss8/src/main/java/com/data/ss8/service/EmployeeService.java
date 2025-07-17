package com.data.ss8.service;

import com.data.ss8.model.dto.request.EmployeeRequestDto;
import com.data.ss8.model.dto.response.EmployeeResponseDto;
import com.data.ss8.model.entity.Employee;
import com.data.ss8.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setFullname(employeeRequestDto.getFullname());
        employee.setPhone(employeeRequestDto.getPhone());
        employee.setAddress(employeeRequestDto.getAddress());
        employee.setPosition(employeeRequestDto.getPosition());
        employee.setSalary(employeeRequestDto.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToResponseDto(savedEmployee);
    }

    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy nhân viên với ID: " + id));

        existingEmployee.setFullname(employeeRequestDto.getFullname());
        existingEmployee.setPhone(employeeRequestDto.getPhone());
        existingEmployee.setAddress(employeeRequestDto.getAddress());
        existingEmployee.setPosition(employeeRequestDto.getPosition());
        existingEmployee.setSalary(employeeRequestDto.getSalary());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return convertToResponseDto(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("Không tìm thấy nhân viên với ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy nhân viên với ID: " + id));
        return convertToResponseDto(employee);
    }

    private EmployeeResponseDto convertToResponseDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setFullname(employee.getFullname());
        dto.setPhone(employee.getPhone());
        dto.setAddress(employee.getAddress());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        return dto;
    }
}
