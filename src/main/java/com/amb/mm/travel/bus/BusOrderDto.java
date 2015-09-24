package com.amb.mm.travel.bus;

import java.math.BigDecimal;
import java.util.List;

import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.OrderStatusType;

public class BusOrderDto {
	
	private BusOffer offer;
	private Customer customer;
	private String comment;
	private BigDecimal totalAmount;
	private BigDecimal unitPrice;
	private Integer quantity;
	private OrderStatusType orderStatus =  OrderStatusType.OPENED;
	private List<BusOrderItem> tickets;
	private String bookingRefId;
	
	public BusOrderDto() {
	}
	
	public BusOrderDto(BusOffer offer, Customer customer, List<BusOrderItem> tickets, String comment) {
		this.offer = offer;
		this.customer = customer;
		this.tickets = tickets;
		this.comment = comment;
	}
	
	public BusOrderDto(String bookingRefId, BusOffer offer, Customer customer, List<BusOrderItem> tickets, String comment, BigDecimal unitPrice, BigDecimal totalAmount) {
		this.bookingRefId = bookingRefId;
		this.offer = offer;
		this.customer = customer;
		this.tickets = tickets;
		this.comment = comment;
		this.unitPrice = unitPrice;
		this.totalAmount = totalAmount;
	}

	public BusOffer getOffer() {
		return offer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getComment() {
		return comment;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public OrderStatusType getOrderStatus() {
		return orderStatus;
	}

	public List<BusOrderItem> getTickets() {
		return tickets;
	}

	public void setOffer(BusOffer offer) {
		this.offer = offer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setOrderStatus(OrderStatusType orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setTickets(List<BusOrderItem> tickets) {
		this.tickets = tickets;
	}

	public String getBookingRefId() {
		return bookingRefId;
	}

	public void setBookingRefId(String bookingRefId) {
		this.bookingRefId = bookingRefId;
	}

	

}
