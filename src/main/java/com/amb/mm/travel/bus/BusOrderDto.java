package com.amb.mm.travel.bus;

import java.math.BigDecimal;
import java.util.List;

import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.OrderStatusType;

public class BusOrderDto {
	
	private Long offerId;
	private Customer customer;
	private String comment;
	private BigDecimal totalAmount;
	private BigDecimal unitPrice;
	private OrderStatusType orderStatus =  OrderStatusType.OPENED;
	private List<Customer> passengers;
	
	public BusOrderDto() {
	}
	
	public BusOrderDto(Long offerId, Customer customer, List<Customer> passengers, String comment) {
		this.offerId = offerId;
		this.customer = customer;
		this.passengers = passengers;
		this.comment = comment;
	}
	public BusOrderDto(Long offerId, Customer customer, List<Customer> passengers, String comment, BigDecimal unitPrice, BigDecimal totalAmount) {
		this.offerId = offerId;
		this.customer = customer;
		this.passengers = passengers;
		this.comment = comment;
		this.unitPrice = unitPrice;
		this.totalAmount = totalAmount;
	}

	public Long getOfferId() {
		return offerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getComment() {
		return comment;
	}

	public OrderStatusType getOrderStatus() {
		return orderStatus;
	}

	public List<Customer> getPassengers() {
		return passengers;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	

}
