package com.data.ss6.service;

import com.data.ss6.entity.Employee;
import com.data.ss6.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    public boolean deleteById(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Employee update(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            return null;
        }
    }
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
