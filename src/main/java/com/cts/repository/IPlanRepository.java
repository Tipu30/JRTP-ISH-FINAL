package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {

}
