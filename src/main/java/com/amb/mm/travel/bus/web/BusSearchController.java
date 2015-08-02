package com.amb.mm.travel.bus.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import com.amb.mm.travel.bus.BusSchedule;
import com.amb.mm.travel.bus.service.BusSearchCriteria;
import com.amb.mm.travel.bus.service.BusService;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.core.service.POIRepository;
import com.amb.mm.travel.core.service.RouteRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/bus/search/api")
public class BusSearchController {

	@Autowired
    BusService service;
	
	@Autowired
    RouteRepository routeRepository;
	
	@Autowired
    POIRepository poiRepository;

	@RequestMapping("/findByRoute")
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
		
		List<BusSchedule> result = service.findBusSchdules(criteria);
		return result;
	}
	
	@RequestMapping("/routes")
	@Transactional(readOnly = true)
	public Iterable<Route> listBusRoute() {
		
		Iterable<Route> routes = routeRepository.findAll();
		return routes;
	}

}
