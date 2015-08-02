package com.amb.mm.travel.core;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Order extends GeneratedIdEntry{
	@Column(nullable = false)
	private BigDecimal unitPrice;
	
	@Column(nullable = false)
	private Long quantity;
	
	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private OrderStatusType orderStatus;

	public Order() {
		
	}
	
	public Order(BigDecimal unitPrice, Long quantity, OrderStatusType status) {
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.orderStatus = status;
		this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Long getQuantity() {
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
