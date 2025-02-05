package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.CitizenAppRegistrationEntity;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenAppRegistrationEntity, Integer> {

}
