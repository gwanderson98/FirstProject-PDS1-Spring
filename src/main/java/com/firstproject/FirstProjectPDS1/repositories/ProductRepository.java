package com.firstproject.FirstProjectPDS1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.FirstProjectPDS1.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
