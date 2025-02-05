package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {
	public DcEducationEntity findByCaseNo(int caseNo);
}
