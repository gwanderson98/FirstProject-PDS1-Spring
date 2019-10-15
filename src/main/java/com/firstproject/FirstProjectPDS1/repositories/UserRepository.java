package com.firstproject.FirstProjectPDS1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.FirstProjectPDS1.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
