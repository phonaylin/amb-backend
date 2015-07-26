package com.amb.mm.travel.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BusOperators")
public class BusOperator extends GeneratedIdEntry{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}