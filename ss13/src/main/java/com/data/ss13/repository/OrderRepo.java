package com.data.ss13.repository;

import com.data.ss13.entity.bt.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
