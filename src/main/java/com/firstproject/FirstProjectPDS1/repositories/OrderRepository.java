package com.firstproject.FirstProjectPDS1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.FirstProjectPDS1.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
