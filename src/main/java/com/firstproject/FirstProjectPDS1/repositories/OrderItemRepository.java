package com.firstproject.FirstProjectPDS1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.FirstProjectPDS1.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
}
