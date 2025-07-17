package com.data.ss8.repository;

import com.data.ss8.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
    List<PaymentSlip> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<PaymentSlip> findByTitleContainingIgnoreCase(String title);
    @Query("SELECT SUM(p.money) FROM PaymentSlip p WHERE p.createdAt BETWEEN :start AND :end")
    Double getTotalAmountByDateRange(LocalDateTime start, LocalDateTime end);
    List<PaymentSlip> findAllByOrderByCreatedAtDesc();
    // Chi tiêu theo tháng trong năm
    @Query("SELECT MONTH(p.createdAt) as month, YEAR(p.createdAt) as year, SUM(p.money) as totalExpense " +
            "FROM PaymentSlip p " +
            "WHERE YEAR(p.createdAt) = :year " +
            "GROUP BY MONTH(p.createdAt), YEAR(p.createdAt) " +
            "ORDER BY month")
    List<Object[]> getMonthlyExpensesByYear(@Param("year") Integer year);

    // Tổng chi tiêu trong tháng hiện tại
    @Query("SELECT SUM(p.money) FROM PaymentSlip p " +
            "WHERE MONTH(p.createdAt) = :month AND YEAR(p.createdAt) = :year")
    Double getCurrentMonthExpenses(@Param("month") Integer month, @Param("year") Integer year);
}
