package com.amb.mm.travel.bus;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.GeneratedIdEntry;
import com.amb.mm.travel.core.OrderStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BusOrderItem extends GeneratedIdEntry{
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	private BusOrder busOrder;
	
	@ManyToOne(optional = false)
	private Customer passenger;
	
	private String ticketNumber;
	
	private Date plannedTravelDate;
	
	@ManyToOne(optional = true)
	private BusSchedule confirmedSchedule;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private OrderStatusType orderStatus;
	
	public BusOrderItem() {
		
	}
	
	public BusOrderItem(Customer passenger) {
		super();
		this.passenger = passenger;
	}
	
	public BusOrderItem(BusOrder busOrder, Customer passenger, OrderStatusType orderStatus) {
		super();
		this.busOrder = busOrder;
		this.passenger = passenger;
		this.orderStatus = orderStatus;
	}

	public BusOrderItem(Customer passenger, Date plannedTravelDate, OrderStatusType orderStatus) {
		super();
		this.passenger = passenger;
		this.plannedTravelDate = plannedTravelDate;
		this.orderStatus = orderStatus;
	}
	
	// Getters setters
	
	public BusOrder getBusOrder() {
		return busOrder;
	}

	public Customer getPassenger() {
		return passenger;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setBusOrder(BusOrder busOrder) {
		this.busOrder = busOrder;
	}

	public void setPassenger(Customer passenger) {
		this.passenger = passenger;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Date getPlannedTravelDate() {
		return plannedTravelDate;
	}

	public void setPlannedTravelDate(Date plannedTravelDate) {
		this.plannedTravelDate = plannedTravelDate;
	}
}
