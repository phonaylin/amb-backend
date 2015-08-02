package com.amb.mm.travel.bus.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.BusOrder;
import com.amb.mm.travel.bus.BusSchedule;
import com.amb.mm.travel.bus.service.BusOrderService;
import com.amb.mm.travel.bus.service.BusSearchCriteria;
import com.amb.mm.travel.bus.service.BusService;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.core.service.POIRepository;
import com.amb.mm.travel.core.service.RouteRepository;
import com.google.common.net.HttpHeaders;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/bus/offers")
public class BusOrderController {

	@Autowired
    BusOrderService service;
	
	@Autowired
    RouteRepository routeRepository;
	
	@Autowired
    POIRepository poiRepository;

	@RequestMapping("")
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
		
		List<BusOffer> result = service.findBusOffers(route);
		return result;
	}
	
	@RequestMapping(value="/api/order", method=RequestMethod.POST)
	@Transactional
	public BusOrder createBusOrder(@Valid @RequestBody BusOrder order) {
		
		//TODO to validate the order
		
		return service.placeOrder(order);
	}

}
