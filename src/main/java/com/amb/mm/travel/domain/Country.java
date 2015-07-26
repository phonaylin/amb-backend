package com.amb.mm.travel.domain;

import javax.persistence.Entity;

@Entity
public class Country extends GeneratedIdEntry{

	private String name;

	private String shortDescription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
