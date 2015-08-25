package com.amb.mm.travel.bus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.GeneratedIdEntry;

@Entity
public class BusOrderItem extends GeneratedIdEntry{
	
	@ManyToOne(fetch=FetchType.LAZY)
	private BusOrder busOrder;
	
	@OneToOne(optional = false)
	private Customer passenger;
	
	private String ticketNumber;
	
	public BusOrderItem() {
		
	}
	
	public BusOrderItem(Customer passenger) {
		super();
		this.passenger = passenger;
	}
	
	public BusOrderItem(BusOrder busOrder, Customer passenger) {
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
