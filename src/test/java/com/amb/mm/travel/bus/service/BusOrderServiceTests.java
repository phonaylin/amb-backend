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
import com.amb.mm.travel.bus.BusBookingDto;
import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.BusOrderDto;
import com.amb.mm.travel.bus.BusOrderItem;
import com.amb.mm.travel.bus.BusType;
import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.core.service.CityRepository;
import com.amb.mm.travel.core.service.CustomerRepository;
import com.amb.mm.travel.core.service.POIRepository;
import com.amb.mm.travel.core.service.RouteRepository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmbApplication.class)
public class BusOrderServiceTests {

	@Autowired
	BusOrderService service;

	@Autowired
	BusOrderRepository orderRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	BusOfferRepository offerRepo;
	
	BusOffer offerOne, offerTwo;
	
	Customer customerOne, customerTwo;
	
	
	@Before
	public void setUp() {
		offerOne = offerRepo.findOne(1L);
		
		offerTwo = offerRepo.findOne(2L);
		
		customerOne = customerRepo.findOne(1L);
		
		customerTwo = customerRepo.findOne(2L);
		
//		List<BusOrderItem> tickets = new ArrayList<BusOrderItem>();
//		tickets.add(new BusOrderItem(customerOne));
//		tickets.add(new BusOrderItem(customerTwo));
//		
//		goodOrder = new BusOrderDto(offerOne, customerOne, tickets, "Whateverr!!");
//		
//		badOrder = new BusOrderDto(new BusOffer(), customerOne, tickets, "What so everr!!");
		
	}
	
	@Test
	public void testPlaceGoodOrder() {
		List<BusOrderItem> tickets = new ArrayList<BusOrderItem>();
		tickets.add(new BusOrderItem(customerOne));
		tickets.add(new BusOrderItem(customerTwo));
		
		BusOrderDto goodOrder = new BusOrderDto(offerOne, customerOne, tickets, "Whateverr!!");
		
		BusOrderDto savedOrder = this.service.placeOrder(goodOrder);
		
		assertThat(savedOrder.getOffer(), is(offerOne));
		assertThat(savedOrder.getCustomer().getFirstName(), is(equalTo("Nay Lin")));
		assertThat(savedOrder.getTickets().size(), is(equalTo(2))); // TODO not concrete checking
	}
	
	@Test
	public void testPlaceBadOrder() {
		BusOrderDto badOrder = new BusOrderDto(new BusOffer(), customerOne, new ArrayList<BusOrderItem>(), "What so everr!!");
		
		BusOrderDto savedOrder = this.service.placeOrder(badOrder);
		
		assertThat(savedOrder, is(equalTo(null)));
	}
	
	@Test
	public void testBookOrders() {
		List<BusOrderItem> tickets = new ArrayList<BusOrderItem>();
		tickets.add(new BusOrderItem(customerOne));
		tickets.add(new BusOrderItem(customerTwo));
		
		BusOrderDto goodOrder1 = new BusOrderDto(offerOne, customerOne, tickets, "Whateverr!!");
		
		BusOrderDto goodOrder2 = new BusOrderDto(offerTwo, customerOne, tickets, "Whateverr!!");
		
		BusBookingDto booking = new BusBookingDto();
		booking.setBuyer(customerOne);
		booking.getOrders().add(goodOrder1);
		booking.getOrders().add(goodOrder2);
		
		String bookingRefId = this.service.bookOrders(booking);
		
		final List<BusOrderDto> orders = this.service.findOrdersByBookingRefId(bookingRefId);
		
		assertThat(orders.size(), is(equalTo(2)));
		assertThat(orders.get(0).getCustomer().getFirstName(), is(equalTo("Nay Lin")));
		assertThat(orders.get(0).getOffer(), is(equalTo(offerOne)));
		assertThat(orders.get(1).getOffer(), is(equalTo(offerTwo)));
		assertThat(orders.get(0).getTickets().size(), is(equalTo(2)));
	}
	
	
//	boolean cancelOrder(Long orderId);
//	
//	boolean changeOrder(Long orderId, BusOrderDto order);
//	
//	List<BusOrderDto> findOrdersByCustomer(Long customerId);
}
