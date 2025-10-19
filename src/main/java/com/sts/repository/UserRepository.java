package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.model.User;

// Anotasi @Repository di interface JpaRepository bersifat opsional, 
// tetapi seringkali disertakan untuk kejelasan.
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Method JPA custom bisa ditambahkan di sini, misal:
    // User findByUsername(String username);
}