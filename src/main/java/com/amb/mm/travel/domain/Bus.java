package com.amb.mm.travel.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Bus extends GeneratedIdEntry{
	
	private String licenseNumber;
	private int capacity;
	
	@Enumerated(EnumType.STRING)
	private Type busType;

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

	public enum Type {
        NORMAL,
        VIP,
        EXECUTIVE
    }
}