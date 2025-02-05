package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.PlanEntity;

public interface AdminRepository extends JpaRepository<PlanEntity, Integer> {
	
}
