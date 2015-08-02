package com.amb.mm.travel.bus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.BusOrder;
import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.OrderStatusType;
import com.amb.mm.travel.core.Route;

@Component("busOrderService")
@Transactional
public class BusOrderServiceImpl implements BusOrderService{

	private final BusOrderRepository orderRepository;
	
	private final BusOfferRepository offerRepository;
	
	
	@Autowired
	public BusOrderServiceImpl(BusOrderRepository orderRepository, BusOfferRepository offerRepository) {
		this.orderRepository = orderRepository;
		this.offerRepository = offerRepository;
	}
	
	@Override
	public List<BusOffer> findBusOffers(Route route) {
		Assert.notNull(route, "Route must not be null");
		
		List<BusOffer> r = this.offerRepository.findByRoute(route);
		return r;
	}

	@Override
	public BusOrder placeOrder(BusOrder order) {
		Assert.notNull(order, "Order must not be null");
		
		order.setOrderStatus(OrderStatusType.OPENED);
		return this.orderRepository.save(order);
	}

	@Override
	public BusOrder cancelOrder(BusOrder order) {
		Assert.notNull(order, "Order must not be null");
		
		order.setOrderStatus(OrderStatusType.CANCELLED);
		return this.orderRepository.save(order);
	}

	//TODO do we even need this?
	@Override
	public BusOrder changeOrder(BusOrder order) {
		order.setOrderStatus(OrderStatusType.CANCELLED);
		return this.orderRepository.save(order);
	}

	@Override
	public List<BusOrder> findBusOrders(Customer customer) {
		return orderRepository.findByCustomer(customer);
	}
	
}
