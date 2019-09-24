package com.firstproject.FirstProjectPDS1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstproject.FirstProjectPDS1.dto.ProductDTO;
import com.firstproject.FirstProjectPDS1.entities.Product;
import com.firstproject.FirstProjectPDS1.repositories.ProductRepository;
import com.firstproject.FirstProjectPDS1.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<ProductDTO> findAll(){
		List<Product> list =  repository.findAll();
		return list.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}	
	
	@Transactional
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity =  obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}

}
