package com.capstone.application.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.capstone.application.model.PhysicianAvailabiityModel;
import com.capstone.application.repository.PhysicianAvailabilityRepository;
import com.capstone.application.service.PhysicianAvailabilityService;

@Service
public class PhysicianAvailabilityServiceImpl implements PhysicianAvailabilityService{
	
	private PhysicianAvailabilityRepository physicianAvailabilityRepository;

	public PhysicianAvailabilityServiceImpl(PhysicianAvailabilityRepository physicianAvailabilityRepository) {
		super();
		this.physicianAvailabilityRepository = physicianAvailabilityRepository;
	}

	@Override
	public List<PhysicianAvailabiityModel> findAll() {
		return physicianAvailabilityRepository.findAll();
	}
	
	public PhysicianAvailabiityModel update(PhysicianAvailabiityModel physicianAvailabiity) {
		PhysicianAvailabiityModel p=physicianAvailabiity;
		List<PhysicianAvailabiityModel>l=physicianAvailabilityRepository.findAll();
		for(PhysicianAvailabiityModel i:l) {
			if(i.getPhysicianEmail().equals(physicianAvailabiity.getPhysicianEmail())) {
				p=i;
				break;
				
			}
		}
		
		p.setStartDate(physicianAvailabiity.getStartDate());
		p.setEndDate(physicianAvailabiity.getEndDate());
		
		PhysicianAvailabiityModel updateResponse = physicianAvailabilityRepository.save(p);
        return updateResponse;
	}

	@Override
	public boolean deletePhysician(String physicianEmail) {
		physicianAvailabilityRepository.deleteById(physicianEmail);
		return true;
	}

	
}
