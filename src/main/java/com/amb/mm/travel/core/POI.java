package com.amb.mm.travel.core;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class POI extends GeneratedIdEntry{
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	private String map;
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getMap() {
		return map;
	}

}
