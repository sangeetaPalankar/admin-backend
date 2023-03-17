package com.capstone.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.application.model.Patient;
import com.capstone.application.service.PatientInfoService;

@RestController
public class PatientInfoController {
	
	private PatientInfoService patientInfoService;

	public PatientInfoController(PatientInfoService patientInfoService) 
	{
		super();
		this.patientInfoService = patientInfoService;
	}

	
	@GetMapping("/patient/{patientId}")
	public Optional<Patient> allergyById(@PathVariable int patientId) 
	{
        Optional < Patient > optional = patientInfoService.findById(patientId);
		return optional;
	}
	
	@PostMapping("/patient/{patientId}")
	 public Patient updatePatientInfo(@RequestBody Patient patient) {
        Patient updateResponse = patientInfoService.update(patient);
        return updateResponse;
	}

	
	@GetMapping("/patient")
	public ResponseEntity<List<Patient>> PatientList() {
		List <Patient> patient = patientInfoService.findAll();
		return new ResponseEntity<>(patient, HttpStatus.OK );
	}

}
