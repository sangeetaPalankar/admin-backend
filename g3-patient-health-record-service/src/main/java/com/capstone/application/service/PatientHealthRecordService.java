package com.capstone.application.service;

import java.util.List;
import java.util.Optional;

import com.capstone.application.dto.VisitDetailsDto;
import com.capstone.application.model.DoctorInfo;
import com.capstone.application.model.Prescription;
import com.capstone.application.model.Tests;
import com.capstone.application.model.VisitDetails;

public interface PatientHealthRecordService {

	public Optional<VisitDetails> findById(Integer patientId);
    public VisitDetails update(VisitDetails visitDetails);
    public Prescription updatePrescription (Prescription prescripion);
	public Tests updateforTest(Tests tests);
	public VisitDetailsDto createVisitDetails(VisitDetailsDto visitDetailsDto);
	public VisitDetailsDto updateVisitDetials(int patientId,VisitDetailsDto visitDetailsDto);
	public List<Prescription> findAllPriscription();
	public void postDoctors() throws Throwable, Exception;
	public void postAdmins()throws Throwable, Exception;
	public void postNurses()throws Throwable, Exception;

}
