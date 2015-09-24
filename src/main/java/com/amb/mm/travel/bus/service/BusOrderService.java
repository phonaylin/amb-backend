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

import java.util.List;

import com.amb.mm.travel.bus.BusBookingDto;
import com.amb.mm.travel.bus.BusOrderDto;

public interface BusOrderService {

	BusOrderDto findOne(Long orderId);
	
	BusOrderDto placeOrder(BusOrderDto order);
	
	boolean cancelOrder(Long orderId);
	
	boolean changeOrder(Long orderId, BusOrderDto order);
	
	List<BusOrderDto> findOrdersByCustomer(Long customerId);

	String bookOrders(BusBookingDto dto);

	BusBookingDto book(BusBookingDto dto);
	
	List<BusOrderDto> findOrdersByBookingRefId(final String bookingRefId);
	
	
}
