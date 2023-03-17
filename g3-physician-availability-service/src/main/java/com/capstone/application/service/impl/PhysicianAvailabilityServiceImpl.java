package com.capstone.application.service.impl;
import java.util.List;
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
		PhysicianAvailabiityModel updateResponse = physicianAvailabilityRepository.save(physicianAvailabiity);
        return updateResponse;
	}

	@Override
	public boolean deletePhysician(String physicianEmail) {
		physicianAvailabilityRepository.deleteById(physicianEmail);
		return true;
	}

	
}
