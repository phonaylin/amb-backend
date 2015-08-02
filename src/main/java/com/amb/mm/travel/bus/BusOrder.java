package com.amb.mm.travel.bus;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.Order;
import com.amb.mm.travel.core.OrderStatusType;

@Entity
public class BusOrder extends Order{
	
	@ManyToOne(optional = false)
	@NaturalId
	private BusOffer busOffer;
	
	@ManyToOne(optional = false)
	@NaturalId
	private Customer customer;

	@ManyToOne(optional = true)
	private BusSchedule confirmedSchedule;
	
	private String comment;
	
	@OneToMany(mappedBy = "busOrder", cascade = CascadeType.ALL)
	private List<BusOrderDetail> orderDetails;
	
	public BusOrder() {
		super();
	}
	
	public BusOrder(BusOffer busOffer, Customer customer, Long quantity, OrderStatusType orderStatus) {
		super(busOffer.getFare(), quantity, orderStatus);
		this.busOffer = busOffer;
		this.customer = customer;
	}
	
	public BusOrder(BusOffer busOffer, Customer customer, Long quantity) {
		super(busOffer.getFare(), quantity, OrderStatusType.OPENED);
		this.busOffer = busOffer;
		this.customer = customer;
	}
	

	public BusOffer getBusOffer() {
		return busOffer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public BusSchedule getConfirmedSchedule() {
		return confirmedSchedule;
	}

	public String getComment() {
		return comment;
	}

	public List<BusOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setConfirmedSchedule(BusSchedule confirmedSchedule) {
		this.confirmedSchedule = confirmedSchedule;
	}
	
	public void setOrderDetails(List<BusOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	

}
