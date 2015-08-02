package com.amb.mm.travel.core;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Route extends GeneratedIdEntry{
	
	@ManyToOne(optional = false)
	private POI originPOI;
	
	@ManyToOne(optional = false)
	private POI destPOI;
	
	
	public POI getOriginPOI() {
		return originPOI;
	}

	public POI getDestPOI() {
		return destPOI;
	}
}
