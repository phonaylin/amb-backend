/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amb.mm.travel;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

/**
 * Integration test to run the application.
 *
 * @author Oliver Gierke
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmbApplication.class)
@WebAppConfiguration
// Enable JMX so we can test the MBeans (you can't do this in a properties file)
@TestPropertySource(properties = { "spring.jmx.enabled:true",
		"spring.datasource.jmx-enabled:true" })
@ActiveProfiles("scratch")
// Separate profile for web tests to avoid clashing databases
public class AmbApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

//	@Test
//	public void testHome() throws Exception {
//
//		this.mvc.perform(get("/")).andExpect(status().isOk())
//				.andExpect(content().string("Bath"));
//	}

	@Test
	public void testJmx() throws Exception {
		assertEquals(
				1,
				ManagementFactory
						.getPlatformMBeanServer()
						.queryMBeans(new ObjectName("amb.mm.travel:type=ConnectionPool,*"),
								null).size());
	}
	
	/* BUS Controller tests
	 * */
	
//	@Test
//	public void testBuses() throws Exception {
//
//		this.mvc.perform(get("/buses")).andExpect(status().isOk());
//	}

	@Test
	public void testFindBusSchedules() throws Exception {

		this.mvc.perform(get("/bus/api/findByRoute?from=1&to=2&date=2015-08-01")).andExpect(status().isOk());
	}
	
	@Test
	public void testFindAllBusRoutes() throws Exception {

		this.mvc.perform(get("/bus/routes")).andExpect(status().isOk());
	}
	
	@Test
	public void testFindAllBusOffers() throws Exception {

		this.mvc.perform(get("/bus/offers?from=1&to=2&date=2015-08-01")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetOrder1() throws Exception {
		this.mvc.perform(get("/bus/orders?customerId=1")).andExpect(status().isOk());
	}
	
//	@Test
//	public void testPlaceBusOrder() throws Exception {
//		this.mvc.perform(post("/bus/order")..(name, values)).andExpect(status().isOk());
//	}
}
