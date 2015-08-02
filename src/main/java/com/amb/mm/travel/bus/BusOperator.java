package com.amb.mm.travel.bus;

import javax.persistence.Entity;

import com.amb.mm.travel.core.GeneratedIdEntry;

@Entity
public class BusOperator extends GeneratedIdEntry{

	private String name;

	public BusOperator() {
		
	}
	
	public String getName() {
		return name;
	}

}