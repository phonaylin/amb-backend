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
//package com.amb.mm.travel.bus.service;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.amb.mm.travel.AmbApplication;
//import com.amb.mm.travel.bus.BusOffer;
//import com.amb.mm.travel.bus.BusOrderDto;
//import com.amb.mm.travel.bus.BusType;
//import com.amb.mm.travel.core.Customer;
//import com.amb.mm.travel.core.POI;
//import com.amb.mm.travel.core.Route;
//import com.amb.mm.travel.core.service.CityRepository;
//import com.amb.mm.travel.core.service.CustomerRepository;
//import com.amb.mm.travel.core.service.POIRepository;
//import com.amb.mm.travel.core.service.RouteRepository;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AmbApplication.class)
//public class MailingServiceTests {
//
//	@Autowired
//	MailingService service;
//
//	@Autowired
//	BusOrderService orderService;
//	
//	BusOrderDto goodOrder;
//	
//	BusOrderDto badOrder;
//	
//	@Before
//	public void setUp() {
//		List<Customer> passengers = new ArrayList<Customer>();
//		passengers.add(new Customer("Passenger", "A"));
//		passengers.add(new Customer("Passenger", "B"));
//		passengers.add(new Customer("Passenger", "C"));
//		
//		goodOrder = new BusOrderDto(1L, new Customer("Good", "Guy", "phonaylin@gmail.com", "123"), passengers, "Whateverr!!");
//	}
//	
//	@Test
//	public void testSendConfirmationEmail() {
//		BusOrderDto savedOrder = this.orderService.placeOrder(goodOrder);
//		
//		assertThat(savedOrder.getOfferId(), is(1L));
//		assertThat(savedOrder.getCustomer().getFirstName(), is(equalTo("Good")));
//		assertThat(savedOrder.getPassengers().size(), is(equalTo(3))); // TODO not concrete checking
//	}
	
//}
