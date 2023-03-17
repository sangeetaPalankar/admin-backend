package com.capstone.application.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="physician_availability",schema="physician_availability_db")
public class PhysicianAvailabiityModel 
{
	@Id
	@Column(name="physician_email")
	private String physicianEmail;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="availability")
	private boolean availability;

	public PhysicianAvailabiityModel(String physicianEmail, String startDate, String endDate, boolean availability) {
		super();
		this.physicianEmail = physicianEmail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.availability = availability;
	}

	public PhysicianAvailabiityModel() {
		super();
	}

	public String getPhysicianEmail() {
		return physicianEmail;
	}

	public void setPhysicianEmail(String physicianEmail) {
		this.physicianEmail = physicianEmail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	
	
	
	
	
}
