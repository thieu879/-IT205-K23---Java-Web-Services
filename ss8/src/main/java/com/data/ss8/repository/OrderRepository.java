package com.data.ss8.repository;

import com.data.ss8.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByEmployeeId(Long employeeId);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    @Query("SELECT SUM(o.totalMoney) FROM Order o WHERE o.createdAt BETWEEN :start AND :end")
    Double getTotalRevenueByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    List<Order> findAllByOrderByCreatedAtDesc();
    // Top 10 khách hàng tiêu nhiều tiền nhất
    @Query("SELECT o.customer.id, o.customer.fullName, o.customer.email, o.customer.phone, " +
            "SUM(o.totalMoney) as totalSpent, COUNT(o.id) as numberOfOrders " +
            "FROM Order o " +
            "GROUP BY o.customer.id, o.customer.fullName, o.customer.email, o.customer.phone " +
            "ORDER BY totalSpent DESC")
    List<Object[]> getTopCustomersBySpending();

    // Doanh thu theo tháng trong năm
    @Query("SELECT MONTH(o.createdAt) as month, YEAR(o.createdAt) as year, " +
            "SUM(o.totalMoney) as totalRevenue, COUNT(o.id) as numberOfOrders " +
            "FROM Order o " +
            "WHERE YEAR(o.createdAt) = :year " +
            "GROUP BY MONTH(o.createdAt), YEAR(o.createdAt) " +
            "ORDER BY month")
    List<Object[]> getMonthlyRevenueByYear(@Param("year") Integer year);

    // Top nhân viên theo doanh thu trong tháng
    @Query("SELECT o.employee.id, o.employee.fullname, o.employee.position, " +
            "SUM(o.totalMoney) as totalRevenue, COUNT(o.id) as numberOfOrders " +
            "FROM Order o " +
            "WHERE MONTH(o.createdAt) = :month AND YEAR(o.createdAt) = :year " +
            "GROUP BY o.employee.id, o.employee.fullname, o.employee.position " +
            "ORDER BY totalRevenue DESC")
    List<Object[]> getTopEmployeesByRevenueInMonth(@Param("month") Integer month, @Param("year") Integer year);
}
