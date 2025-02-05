package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer>{
	public DcIncomeEntity findByCaseNo(int caseNo);
}
