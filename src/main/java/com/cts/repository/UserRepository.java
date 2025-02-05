package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	    Optional<UserEntity> findByEmail(String email); // For login validation
	    boolean existsByEmail(String email); 
	    boolean existsByRole(String role);
}
