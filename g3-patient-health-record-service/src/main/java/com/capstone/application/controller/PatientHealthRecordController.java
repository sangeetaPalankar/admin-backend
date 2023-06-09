package com.capstone.application.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.application.dto.VisitDetailsDto;
import com.capstone.application.model.AdminInfo;
import com.capstone.application.model.NurseInfo;
import com.capstone.application.model.Patient;
import com.capstone.application.model.Prescription;
import com.capstone.application.model.Tests;
import com.capstone.application.model.VisitDetails;
import com.capstone.application.service.PatientHealthRecordService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class PatientHealthRecordController {
	
	private PatientHealthRecordService patientHealthRecordService;
	
	
	public PatientHealthRecordController(PatientHealthRecordService patientHealthRecordService) {
		super();
		this.patientHealthRecordService = patientHealthRecordService;
	}

	@GetMapping("/patient/{patientId}/health-records")
	public Optional<VisitDetails> healthRecordsById(@PathVariable int patientId) 
	{
        Optional < VisitDetails > optional = patientHealthRecordService.findById(patientId);
		return optional;
	}
	
	@PostMapping("/patient/health-records")
	 public VisitDetailsDto insertVisitDetials(@RequestBody VisitDetailsDto visitDetailsDto) {
		return patientHealthRecordService.createVisitDetails(visitDetailsDto);
	}
	
	@PutMapping("/patient/{patientId}/health-records")
	public VisitDetailsDto updatePatientInforDoctors(@PathVariable int patientId, @RequestBody VisitDetailsDto visitDetailsDto) {
		return patientHealthRecordService.updateVisitDetials(patientId,visitDetailsDto );
	}
	
	@PostMapping("/patient/{visitId}/tests")
	public Tests updateTest(@RequestBody Tests tests)
	{
		Tests updateResponse=patientHealthRecordService.updateforTest(tests);
		return updateResponse;
		
	}
	
	@PostMapping("/patient/{visitId}/prescription")
	public Prescription updatePrescription(@RequestBody Prescription prescription) {
		Prescription updateResponse=patientHealthRecordService.updatePrescription(prescription);
		return updateResponse;
	}
	
	@GetMapping("/patient/prescription")
	public List<Prescription> findAllPrescription(){
		return patientHealthRecordService.findAllPriscription();
	}
	
	
	
	@GetMapping("/addAdmins")
	public void postAdmins() throws Exception, Throwable {
		
		patientHealthRecordService.postAdmins();
		
	}
	
	@GetMapping("/addNurses")
	public void postNurses() throws Exception, Throwable {
		
		patientHealthRecordService.postNurses();
		
	}
	
	@GetMapping("/nurses")
	public ResponseEntity<List<NurseInfo>> NurseList() {
		List <NurseInfo> nurse = patientHealthRecordService.NursefindAll();
		return new ResponseEntity<>(nurse, HttpStatus.OK );
	}
	
	@GetMapping("/admins")
	public ResponseEntity<List<AdminInfo>> AdminList() {
		List <AdminInfo> nurse = patientHealthRecordService.AdminfindAll();
		return new ResponseEntity<>(nurse, HttpStatus.OK );
	}
	
}
