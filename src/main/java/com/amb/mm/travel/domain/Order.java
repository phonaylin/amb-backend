package com.amb.mm.travel.domain;

import java.math.BigDecimal;

public abstract class Order extends GeneratedIdEntry{
	private BigDecimal unitPrice;
	private Long quantity;
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
