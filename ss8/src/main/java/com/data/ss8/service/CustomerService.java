package com.data.ss8.service;

import com.data.ss8.model.dto.request.CustomerRequestDto;
import com.data.ss8.model.dto.response.CustomerResponseDto;
import com.data.ss8.model.entity.Customer;
import com.data.ss8.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        Optional<Customer> existingCustomerByEmail = customerRepository.findByEmail(customerRequestDto.getEmail());
        if (existingCustomerByEmail.isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
        }

        Optional<Customer> existingCustomerByPhone = customerRepository.findByPhone(customerRequestDto.getPhone());
        if (existingCustomerByPhone.isPresent()) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống");
        }

        Customer customer = new Customer();
        customer.setFullName(customerRequestDto.getFullName());
        customer.setPhone(customerRequestDto.getPhone());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setNumberOfPayments(customerRequestDto.getNumberOfPayments());
        customer.setStatus(customerRequestDto.getStatus());
        Customer savedCustomer = customerRepository.save(customer);

        return convertToResponseDto(savedCustomer);
    }

    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + id));

        Optional<Customer> existingCustomerByEmail = customerRepository.findByEmail(customerRequestDto.getEmail());
        if (existingCustomerByEmail.isPresent() && !existingCustomerByEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
        }

        Optional<Customer> existingCustomerByPhone = customerRepository.findByPhone(customerRequestDto.getPhone());
        if (existingCustomerByPhone.isPresent() && !existingCustomerByPhone.get().getId().equals(id)) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống");
        }

        existingCustomer.setFullName(customerRequestDto.getFullName());
        existingCustomer.setPhone(customerRequestDto.getPhone());
        existingCustomer.setEmail(customerRequestDto.getEmail());
        existingCustomer.setNumberOfPayments(customerRequestDto.getNumberOfPayments());
        existingCustomer.setStatus(customerRequestDto.getStatus());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return convertToResponseDto(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + id));

        customer.setStatus(false);
        customerRepository.save(customer);
    }

    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findActiveCustomers();
        return customers.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + id));
        return convertToResponseDto(customer);
    }

    public List<CustomerResponseDto> getCustomersByStatus(Boolean status) {
        List<Customer> customers = customerRepository.findByStatus(status);
        return customers.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private CustomerResponseDto convertToResponseDto(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setFullName(customer.getFullName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setNumberOfPayments(customer.getNumberOfPayments());
        dto.setStatus(customer.getStatus());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }
}
