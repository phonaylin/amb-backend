package com.amb.mm.travel.bus;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.amb.mm.travel.core.GeneratedIdEntry;
import com.amb.mm.travel.core.Route;

@Entity
public class BusSchedule extends GeneratedIdEntry{
	
	@ManyToOne(optional=false)
	private BusOperator busOperator;
	
	@ManyToOne(optional=false)
	private Route route;
	
	@Column(nullable = false)
	private int dayOfWeek; // Starts from 1-Sunday to 7-Saturday

	@Column(nullable = false)
	private Date departureTime;
	
	@Column(nullable = false)
	private BusType busType;

	// TODO: Not currently used. See BusOffer.fare
	@Column(nullable = false)
	private BigDecimal fare;
	
	private Date arrivalTime;
	
	private Date activeStartDate;
	
	private Date activeEndDate;
	
	public BusSchedule() {
		
	}
	
	public BusSchedule(BusOperator busOperator, Route route, int dayOfWeek, Date departureTime) {
		super();
		this.busOperator = busOperator;
		this.route = route;
		this.dayOfWeek = dayOfWeek;
		this.departureTime = departureTime;
	}
	
	public BusSchedule(BusOperator busOperator, Route route, int dayOfWeek, Date departureTime, BusType busType) {
		super();
		this.busOperator = busOperator;
		this.route = route;
		this.dayOfWeek = dayOfWeek;
		this.departureTime = departureTime;
		this.busType = busType;
	}

	public BusOperator getBusOperator() {
		return busOperator;
	}

	public Route getBusRoute() {
		return route;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public BusType getBusType() {
		return busType;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public Date getActiveStartDate() {
		return activeStartDate;
	}

	public Date getActiveEndDate() {
		return activeEndDate;
	}

}
