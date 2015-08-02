package com.amb.mm.travel.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.amb.mm.travel.core.GeneratedIdEntry;

@Entity
public class Bus extends GeneratedIdEntry{
	
	@Column(nullable = false)
	private String licenseNumber;
	
	@Column(nullable = false)
	private int capacity;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private BusType busType;

	@ManyToOne
	private BusOperator operator;
	
	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}