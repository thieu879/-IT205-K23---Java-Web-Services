package com.data.ss8.repository;

import com.data.ss8.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
    List<OrderDetail> findByDishId(Long dishId);
    @Query("SELECT od.dish.name, SUM(od.quantity) as totalQuantity FROM OrderDetail od " +
            "WHERE od.order.createdAt BETWEEN :start AND :end " +
            "GROUP BY od.dish.id, od.dish.name " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> getTopSellingDishes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    // Top 10 món ăn bán chạy nhất
    @Query("SELECT od.dish.id, od.dish.name, SUM(od.quantity) as totalQuantity, SUM(od.quantity * od.priceBuy) as totalRevenue " +
            "FROM OrderDetail od " +
            "GROUP BY od.dish.id, od.dish.name " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> getTopSellingDishes();

    // Top 10 món ăn bán chạy nhất theo khoảng thời gian
    @Query("SELECT od.dish.id, od.dish.name, SUM(od.quantity) as totalQuantity, SUM(od.quantity * od.priceBuy) as totalRevenue " +
            "FROM OrderDetail od " +
            "WHERE od.order.createdAt BETWEEN :start AND :end " +
            "GROUP BY od.dish.id, od.dish.name " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> getTopSellingDishesByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
