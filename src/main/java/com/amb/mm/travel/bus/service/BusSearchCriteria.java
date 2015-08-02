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

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.Assert;

import com.amb.mm.travel.bus.BusType;
import com.amb.mm.travel.core.Route;

public class BusSearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private BusType busType;

	private String operatorName;
	
	private Route busRoute;
	
	private Date departureDate;
	
	public BusSearchCriteria() {
	}

	public BusSearchCriteria(Route route) {
		Assert.notNull(route, "Route must not be null");
		this.busRoute = route;
	}
	
	public BusSearchCriteria(Route route, Date departureDate) {
		Assert.notNull(route, "Route must not be null");
		Assert.notNull(departureDate, "Departure Date must not be null");
		this.busRoute = route;
		this.departureDate = departureDate;
	}
	
	public BusSearchCriteria(String operatorName) {
		Assert.notNull(operatorName, "Opeartor Name must not be null");
		this.operatorName = operatorName;
	}
	
	public BusSearchCriteria(String operatorName, BusType busType) {
		Assert.notNull(operatorName, "Opeartor Name must not be null");
		Assert.notNull(busType, "Bus Type must not be null");
		this.operatorName = operatorName;
		this.busType = busType;
	}

	public BusType getBusType() {
		return busType;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Route getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(Route busRoute) {
		this.busRoute = busRoute;
	}
	
	
}
