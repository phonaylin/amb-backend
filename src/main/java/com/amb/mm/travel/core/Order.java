package com.amb.mm.travel.core;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Order extends GeneratedIdEntry{
	@Column(nullable = false)
	protected BigDecimal unitPrice;
	
	@Column(nullable = false)
	protected int quantity;
	
	@Column(nullable = false)
	protected BigDecimal totalPrice;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private OrderStatusType orderStatus;

	public Order() {
		
	}
	
	public Order(BigDecimal unitPrice, OrderStatusType status) {
		this.unitPrice = unitPrice;
		this.orderStatus = status;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public OrderStatusType getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatusType orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
