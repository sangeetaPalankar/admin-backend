package com.capstone.application.model;

import jakarta.persistence.*;

@Entity
@Table(name="nurseinfo")
public class NurseInfo {
	
	@Id
	@Column
	private String nurse_email;
	
	

	public NurseInfo() {
		super();
	}



	public NurseInfo(String nurse_email) {
		super();
		this.nurse_email = nurse_email;
	}



	public String getNurse_email() {
		return nurse_email;
	}



	public void setNurse_email(String nurse_email) {
		this.nurse_email = nurse_email;
	}
	
	
	

}
