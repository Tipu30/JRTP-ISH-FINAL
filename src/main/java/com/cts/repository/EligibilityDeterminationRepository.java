package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.EligibilityDetailsEntity;

public interface EligibilityDeterminationRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {

}
