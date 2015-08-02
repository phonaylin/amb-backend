package com.amb.mm.travel.bus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.GeneratedIdEntry;

@Entity
public class BusOrderDetail extends GeneratedIdEntry{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NaturalId
	private BusOrder busOrder;
	
	@ManyToOne(optional = false)
	@NaturalId
	private Customer passenger;
	
	private String ticketNumber;
	
	public BusOrderDetail() {
		
	}
	
	public BusOrderDetail(BusOrder busOrder, Customer passenger) {
		super();
		this.busOrder = busOrder;
		this.passenger = passenger;
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
}
