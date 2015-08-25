/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amb.mm.travel.bus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.amb.mm.travel.AmbApplication;
import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.BusOrder;
import com.amb.mm.travel.bus.BusOrderItem;
import com.amb.mm.travel.bus.BusType;
import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.OrderStatusType;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.core.service.CityRepository;
import com.amb.mm.travel.core.service.CustomerRepository;
import com.amb.mm.travel.core.service.POIRepository;
import com.amb.mm.travel.core.service.RouteRepository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmbApplication.class)
public class BusOrderRepositoryTests {

	@Autowired
	BusOrderRepository orderRepository;

	@Autowired
	BusOfferRepository offerRepository;

	@Autowired
	BusScheduleRepository scheduleRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	POIRepository poiRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	BusOffer offer;
	Route route;
	Customer customer;
	Customer passenger;
	
	@Before
	public void setUp() {
		POI originPOI =  poiRepository.findOne(1L);
		POI destPOI =  poiRepository.findOne(2L);
		this.route = routeRepository.findByOriginPOIAndDestPOI(originPOI, destPOI);
		this.offer =  offerRepository.findByRouteAndBusType(this.route, BusType.NORMAL).get(0);
		this.customer = customerRepository.findOne(1L);
		this.passenger = customerRepository.findOne(2L);
	}
	
	@Test
	public void findByBusOfferReturnsNotFound() {
		List<BusOrder> orders = this.orderRepository.findByBusOffer(null);
		assertThat(orders.size(), is(equalTo(0)));
	}
	
	@Test
	public void saveOrderOK() {
		BusOrder myOrder = new BusOrder(this.offer, this.customer, "Comment");
		myOrder.addOrderItem(new BusOrderItem(myOrder, customer));
		myOrder.addOrderItem(new BusOrderItem(myOrder, passenger));
		
		myOrder = this.orderRepository.save(myOrder);
		
		assertThat(myOrder.getBusOffer().getId(), is(equalTo(this.offer.getId())));
		assertThat(myOrder.getCustomer().getId(), is(equalTo(this.customer.getId())));
		assertThat(myOrder.getQuantity(), is(equalTo(2)));
		assertThat(myOrder.getTotalPrice(), is(equalTo(this.offer.getFare().multiply(new BigDecimal(2)))));
	
		assertThat(myOrder.getOrderItems().size(), is(equalTo(2)));
		assertThat(myOrder.getOrderItems().get(0).getPassenger().getFirstName(), is(equalTo(customer.getFirstName())));
		assertThat(myOrder.getOrderItems().get(1).getPassenger().getFirstName(), is(equalTo(passenger.getFirstName())));
	}
	
	@Test
	public void changeOrderStatusOK() {
		BusOrder myOrder = this.orderRepository.findByBusOfferAndCustomer(offer, customer).get(0);
		myOrder.setConfirmedSchedule(this.scheduleRepository.findByRoute(this.route).get(0));
		myOrder.setOrderStatus(OrderStatusType.CONFIRMED);
		myOrder = this.orderRepository.save(myOrder);
		
		assertThat(myOrder.getOrderStatus(), is(equalTo(OrderStatusType.CONFIRMED)));
		assertThat(myOrder.getConfirmedSchedule().getBusRoute().getId(), is(equalTo(this.route.getId())));
	}
	
	@Test
	public void deletOrder() {
		BusOrder myOrder = this.orderRepository.findByBusOfferAndCustomer(offer, customer).get(0);
		
		this.orderRepository.delete(myOrder);
		
		assertThat(orderRepository.findOne(myOrder.getId()), is(equalTo(null)));
		
	}
}
