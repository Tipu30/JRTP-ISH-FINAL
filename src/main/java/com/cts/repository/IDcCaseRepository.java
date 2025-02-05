package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.DcCaseEntity;

public interface IDcCaseRepository extends JpaRepository<DcCaseEntity, Integer> {
	public Integer findByCaseNo(int caseNo);
}
