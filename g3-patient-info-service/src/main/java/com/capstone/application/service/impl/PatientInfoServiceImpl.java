package com.capstone.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.application.model.Patient;
import com.capstone.application.repository.PatientInfoRepository;
import com.capstone.application.service.PatientInfoService;

@Service
public class PatientInfoServiceImpl implements PatientInfoService
{

	private PatientInfoRepository patientInfoRepository;
	
	@Autowired
	public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository) {
		super();
		this.patientInfoRepository = patientInfoRepository;
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return patientInfoRepository.findAll();
	}

	@Override
	public Optional<Patient> findById(Integer patientId) {
		// TODO Auto-generated method stub
		return patientInfoRepository.findById(patientId);
	}

	@Override
	public Patient update(Patient patient) {
		// TODO Auto-generated method stub
		Patient updateResponse = patientInfoRepository.save(patient);
        return updateResponse;
	}
	
}
