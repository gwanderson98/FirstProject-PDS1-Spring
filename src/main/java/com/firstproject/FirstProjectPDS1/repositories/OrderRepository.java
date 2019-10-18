package com.firstproject.FirstProjectPDS1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.FirstProjectPDS1.entities.Order;
import com.firstproject.FirstProjectPDS1.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByClient(User client); 
	
}
