package com.capstone.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.capstone.application.model.PhysicianAvailabiityModel;
import com.capstone.application.service.PhysicianAvailabilityService;

@RestController
@CrossOrigin(origins="*")
public class PhysicianAvailabilityControllers {
	
	private PhysicianAvailabilityService physicianAvailabilityService;
	
	public PhysicianAvailabilityControllers(PhysicianAvailabilityService physicianAvailabilityService) {
		super();
		this.physicianAvailabilityService = physicianAvailabilityService;
	}
	
	
	
	
	

	@GetMapping("/physician-availability")
	public ResponseEntity<List<PhysicianAvailabiityModel>> AvailablePhysician() {
		List < PhysicianAvailabiityModel > availablePhysician = physicianAvailabilityService.findAll();
		return new ResponseEntity<>(availablePhysician, HttpStatus.OK );
	}
	
	@GetMapping("/physician-avail")
	public List<PhysicianAvailabiityModel> AvailablePhysician1(@RequestParam boolean availability) {
		
		List < PhysicianAvailabiityModel > availablePhysician = physicianAvailabilityService.findAll();
		List<PhysicianAvailabiityModel> availableP=new ArrayList<>();
		for(PhysicianAvailabiityModel i:availablePhysician)
		{
			if(i.isAvailability()==availability) {
				//System.out.println(i.getPhysicianEmail());
				availableP.add(i);
			}
		}
		
		return availableP;
	}
	
//	@PostMapping("/physician-availability")
//	public PhysicianAvailabiityModel updatedPhysicianAvailability(@RequestBody PhysicianAvailabiityModel physicianAvailabiity) 
//	{
//		PhysicianAvailabiityModel updateResponse = physicianAvailabilityService.update(physicianAvailabiity);
//        return updateResponse;
//	}
//	
	@PutMapping("/physician-availability")
	public ResponseEntity<PhysicianAvailabiityModel> updatedPhysicianAvailabilitys(@RequestBody PhysicianAvailabiityModel physicianAvailabiity) 
	{
		//System.out.printf(physicianAvailabiity.getEndDate().toString());
		//System.out.printf(physicianAvailabiity.getPhysicianEmail().toString());
		//System.out.printf(physicianAvailabiity.getStartDate().toString());
		PhysicianAvailabiityModel updateResponse = physicianAvailabilityService.update(physicianAvailabiity);
		return new ResponseEntity<>(updateResponse, HttpStatus.OK );

	}
	
	@DeleteMapping("/physician-availability/{physicianEmail}")
	public  ResponseEntity<?> deletePhysicianAvailability(@PathVariable("physicianEmail")String physicianEmail) {
		physicianAvailabilityService.deletePhysician(physicianEmail);
		return new ResponseEntity<>(HttpStatus.OK );
	}
	
	
	@GetMapping("/addDoctors")
	public void postDoctors() throws Exception, Throwable {
		
		physicianAvailabilityService.postDoctors();
		
	}
}
