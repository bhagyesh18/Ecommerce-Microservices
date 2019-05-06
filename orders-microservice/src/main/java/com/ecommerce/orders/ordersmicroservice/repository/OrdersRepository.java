package com.ecommerce.orders.ordersmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orders.ordersmicroservice.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
		List<Orders> findByUserId(Long userId);
}
