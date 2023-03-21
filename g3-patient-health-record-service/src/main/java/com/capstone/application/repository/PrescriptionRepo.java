package com.capstone.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.application.model.Prescription;

public interface PrescriptionRepo extends JpaRepository<Prescription, Integer>{

}
