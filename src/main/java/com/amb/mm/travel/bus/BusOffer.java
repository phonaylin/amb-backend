package com.amb.mm.travel.bus;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.amb.mm.travel.core.GeneratedIdEntry;
import com.amb.mm.travel.core.Route;

@Entity
public class BusOffer extends GeneratedIdEntry{
	
	@ManyToOne(optional=false)
	private Route route;
	
	@Column(nullable = false)
	private Date departureTime;
	
	@Column(nullable = false)
	private BusType busType;

	@Column(nullable = false)
	private BigDecimal fare;
	
	private Date arrivalTime;
	
	private Date activeStartDate;
	
	private Date activeEndDate;
	
	public BusOffer() {
		
	}
	
	public BusOffer(Route route, Date departureTime, BusType busType) {
		super();
		this.route = route;
		this.departureTime = departureTime;
	}
	
	public Route getBusRoute() {
		return route;
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
	
	public Route getRoute() {
		return route;
	}

	public BigDecimal getFare() {
		return fare;
	}

}
