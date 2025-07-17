package com.data.ss8.service;

import com.data.ss8.model.dto.request.OrderDetailRequestDto;
import com.data.ss8.model.dto.request.OrderRequestDto;
import com.data.ss8.model.dto.response.OrderDetailResponseDto;
import com.data.ss8.model.dto.response.OrderResponseDto;
import com.data.ss8.model.entity.*;
import com.data.ss8.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final DishRepository dishRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        CustomerRepository customerRepository,
                        EmployeeRepository employeeRepository,
                        DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.dishRepository = dishRepository;
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + orderRequestDto.getCustomerId()));

        Employee employee = employeeRepository.findById(orderRequestDto.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy nhân viên với ID: " + orderRequestDto.getEmployeeId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setTotalMoney(0.0);

        Order savedOrder = orderRepository.save(order);

        double totalMoney = 0.0;
        for (OrderDetailRequestDto detailDto : orderRequestDto.getOrderDetails()) {
            Dish dish = dishRepository.findById(detailDto.getDishId())
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy món ăn với ID: " + detailDto.getDishId()));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setDish(dish);
            orderDetail.setQuantity(detailDto.getQuantity());
            orderDetail.setPriceBuy(detailDto.getPriceBuy());

            orderDetailRepository.save(orderDetail);

            totalMoney += detailDto.getQuantity() * detailDto.getPriceBuy();
        }

        savedOrder.setTotalMoney(totalMoney);
        orderRepository.save(savedOrder);

        customer.setNumberOfPayments(customer.getNumberOfPayments() + 1);
        customerRepository.save(customer);

        Order orderWithDetails = orderRepository.findById(savedOrder.getId())
                .orElseThrow(() -> new NoSuchElementException("Lỗi khi lấy thông tin hóa đơn"));

        return convertToResponseDto(orderWithDetails);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAllByOrderByCreatedAtDesc();
        return orders.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy hóa đơn với ID: " + id));
        return convertToResponseDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrdersByEmployeeId(Long employeeId) {
        List<Order> orders = orderRepository.findByEmployeeId(employeeId);
        return orders.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrdersByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Order> orders = orderRepository.findByCreatedAtBetween(start, end);
        return orders.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Double getTotalRevenueByDateRange(LocalDateTime start, LocalDateTime end) {
        Double total = orderRepository.getTotalRevenueByDateRange(start, end);
        return total != null ? total : 0.0;
    }

    private OrderResponseDto convertToResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setCustomerName(order.getCustomer().getFullName());
        dto.setEmployeeId(order.getEmployee().getId());
        dto.setEmployeeName(order.getEmployee().getFullname());
        dto.setTotalMoney(order.getTotalMoney());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getId());
        List<OrderDetailResponseDto> orderDetailDtos = orderDetails.stream()
                .map(this::convertOrderDetailToResponseDto)
                .collect(Collectors.toList());
        dto.setOrderDetails(orderDetailDtos);

        return dto;
    }

    private OrderDetailResponseDto convertOrderDetailToResponseDto(OrderDetail orderDetail) {
        OrderDetailResponseDto dto = new OrderDetailResponseDto();
        dto.setId(orderDetail.getId());
        dto.setDishId(orderDetail.getDish().getId());
        dto.setDishName(orderDetail.getDish().getName());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setPriceBuy(orderDetail.getPriceBuy());
        dto.setTotalPrice(orderDetail.getQuantity() * orderDetail.getPriceBuy());
        return dto;
    }
}
