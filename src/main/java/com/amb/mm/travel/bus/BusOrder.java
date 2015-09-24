package com.amb.mm.travel.bus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.Order;
import com.amb.mm.travel.core.OrderStatusType;

@Entity
public class BusOrder extends Order{
	
	@ManyToOne(optional = false)
	private BusOffer busOffer;
	
	@ManyToOne(optional = false)
	private Customer customer;
	
	private String comment;
	
	private String bookingRefId;
	
	@OneToMany(mappedBy = "busOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BusOrderItem> orderItems = new ArrayList<BusOrderItem>();
	
	public BusOrder() {
		super();
	}
	
	public BusOrder(String bookingRefId, BusOffer busOffer, Customer customer, String comment, OrderStatusType orderStatus) {
		super(busOffer.getFare(), orderStatus);
		this.bookingRefId = bookingRefId;
		this.busOffer = busOffer;
		this.customer = customer;
		this.comment = comment;
	}
	
	public BusOrder(BusOffer busOffer, Customer customer, String comment) {
		super(busOffer.getFare(), OrderStatusType.OPENED);
		this.busOffer = busOffer;
		this.customer = customer;
		this.comment = comment;
	}

	public BusOffer getBusOffer() {
		return busOffer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public List<Customer> getPassengers() {
		List<Customer> passengers = new ArrayList<Customer>();
		for(BusOrderItem item : this.orderItems) {
			passengers.add(item.getPassenger());
		}
		
		return passengers;
	}
	
	// TODO: to think more further. how about concurrency issue?
	public boolean addOrderItem(BusOrderItem item) {
		item.setBusOrder(this);
		if (this.orderItems.add(item)) {
			this.quantity = this.quantity + 1;
			this.totalPrice = calcTotalPrice(this.quantity, this.unitPrice);
			return true;
		}
		return false;
	}
	
	public boolean removeOrderItem(BusOrderItem item) {
		if (this.orderItems.add(item)) {
			this.quantity = this.quantity + 1;
			this.totalPrice = calcTotalPrice(this.quantity, this.unitPrice);
			return true;
		}
		return false;
	}
	
	public List<BusOrderItem> getOrderItems() {
		return orderItems; // TODO: give the clone items
	}
	
	private static final BigDecimal calcTotalPrice(final int quantity, final BigDecimal unitPrice) {
		if(quantity <= 0) 
			return new BigDecimal(0);
		
		return unitPrice.multiply(new BigDecimal(quantity));
	}

	public String getBookingRefId() {
		return bookingRefId;
	}

	public void setBookingRefId(String bookingRefId) {
		this.bookingRefId = bookingRefId;
	}
}
