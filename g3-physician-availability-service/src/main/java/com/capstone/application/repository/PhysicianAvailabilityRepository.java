package com.capstone.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.application.model.PhysicianAvailabiityModel;

@Repository
public interface PhysicianAvailabilityRepository extends JpaRepository<PhysicianAvailabiityModel, String>{
	


}
