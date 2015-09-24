package com.amb.mm.travel.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.amb.mm.travel.core.City;
import com.amb.mm.travel.core.GeneratedIdEntry;
import com.amb.mm.travel.core.POI;

@Entity
public class BusStation extends GeneratedIdEntry{
	
	@ManyToOne(optional=false)
	private City city;
	
	@Column(nullable = false)
	private String name;
	
	
}