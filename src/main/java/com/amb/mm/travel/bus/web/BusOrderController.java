package com.amb.mm.travel.bus.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import com.amb.mm.travel.bus.BusBookingDto;
import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.BusOrderDto;
import com.amb.mm.travel.bus.BusSchedule;
import com.amb.mm.travel.bus.service.BusOrderService;
import com.amb.mm.travel.bus.service.BusSearchCriteria;
import com.amb.mm.travel.bus.service.BusService;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.core.service.POIRepository;
import com.amb.mm.travel.core.service.RouteRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/bus")
public class BusOrderController {

	@Autowired
    BusOrderService orderService;
	
	@Autowired
    BusService busService;
	
	@Autowired
    RouteRepository routeRepository;
	
	@Autowired
    POIRepository poiRepository;

	@RequestMapping("/api/findByRoute")
	@Transactional(readOnly = true)
	public List<BusSchedule> listBusSchedulesByRoute(@RequestParam("from") Long fromId, 
			@RequestParam("to") Long toId, @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate) {
		
		POI originPOI = poiRepository.findOne(fromId);
		Assert.notNull(originPOI, "From must not be null");
		
		POI destPOI = poiRepository.findOne(toId);
		Assert.notNull(destPOI, "To must not be null");
		
		Assert.notNull(departureDate, "Departure date must not be null");
		
		Route route = routeRepository.findByOriginPOIAndDestPOI(originPOI, destPOI);
		Assert.notNull(route, "Cannot find the route requested");
		
		BusSearchCriteria criteria = new BusSearchCriteria(route, departureDate);
		
		List<BusSchedule> result = busService.findBusSchdules(criteria);
		return result;
	}
	
	@RequestMapping("/routes")
	@Transactional(readOnly = true)
	public Iterable<Route> listBusRoute() {
		
		Iterable<Route> routes = routeRepository.findAll();
		return routes;
	}

	@RequestMapping("/offers")
	@Transactional(readOnly = true)
	public List<BusOffer> listBusOffersByRoute(@RequestParam("from") Long fromId, 
			@RequestParam("to") Long toId, @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate) {
		
		POI originPOI = poiRepository.findOne(fromId);
		Assert.notNull(originPOI, "From must not be null");
		
		POI destPOI = poiRepository.findOne(toId);
		Assert.notNull(destPOI, "To must not be null");
		
		Assert.notNull(departureDate, "Departure date must not be null");
		
		Route route = routeRepository.findByOriginPOIAndDestPOI(originPOI, destPOI);
		Assert.notNull(route, "Cannot find the route requested");
		
		List<BusOffer> result = busService.findBusOffers(route);
		return result;
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	@Transactional(readOnly = true)
	public List<BusOrderDto> getBusOrder(@RequestParam Long customerId) {
		List<BusOrderDto> orders = orderService.findOrdersByCustomer(customerId);
		return orders;
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	@Transactional
	public BusBookingDto createBusOrder(@RequestBody BusBookingDto booking) {
		Assert.notNull(booking.getBuyer(), "Customer must not be null");
		
		return orderService.book(booking);
	}

}
