package com.data.ss13.repository;

import com.data.ss13.entity.bt.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long>{
}