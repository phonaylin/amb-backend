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
import com.amb.mm.travel.bus.BusType;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.core.service.CityRepository;
import com.amb.mm.travel.core.service.POIRepository;
import com.amb.mm.travel.core.service.RouteRepository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmbApplication.class)
public class BusOfferRepositoryTests {

	@Autowired
	BusOfferRepository offerRepository;

	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	POIRepository poiRepository;
	
	POI originPOI;
	POI destPOI;
	Route route;
	
	@Before
	public void setUp() {
		this.originPOI =  poiRepository.findOne(1L);
		this.destPOI =  poiRepository.findOne(2L);
		route = routeRepository.findByOriginPOIAndDestPOI(originPOI, destPOI);
	}
	
	@Test
	public void findByRouteReturnsNotFound() {
		List<BusOffer> offers = this.offerRepository.findByRoute(null);
		assertThat(offers.size(), is(equalTo(0)));
	}
	
	@Test
	public void findByRouteReturnsManyOffers() {
		List<BusOffer> offers = this.offerRepository.findByRoute(route);
		assertThat(offers.size(), is(greaterThan(1)));
	}
	
	
	@Test
	public void findByRouteAndBusTypeReturnOnlyOne() {
		BusType type = BusType.NORMAL;
		
		List<BusOffer> offers = this.offerRepository.findByRouteAndBusType(route, type);
		assertThat(offers.size(), is(equalTo(1)));
	}
}
