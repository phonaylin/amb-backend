package com.amb.mm.travel.bus.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.amb.mm.travel.bus.BusSchedule;
import com.amb.mm.travel.core.Route;
import com.amb.mm.travel.utils.DateTimeHelper;

@Component("busService")
@Transactional
public class BusServiceImpl implements BusService{

	private final BusScheduleRepository busScheduleRepository;
	
	@Autowired
	public BusServiceImpl(BusScheduleRepository busScheduleRepository) {
		this.busScheduleRepository = busScheduleRepository;
	}
	
	@Override
	public List<BusSchedule> findBusSchdules(BusSearchCriteria criteria) {
		Assert.notNull(criteria, "Criteria must not be null");
		final Route route = criteria.getBusRoute();
		final Date departDate = criteria.getDepartureDate();
		
		List<BusSchedule> r = new ArrayList<BusSchedule>();
		if (route != null && departDate != null) {
			r = this.busScheduleRepository.findByRouteAndDayOfWeek(route, DateTimeHelper.getDayOfWeek(departDate));
		}
		else if (route != null) {
			r = this.busScheduleRepository.findByRoute(route);
		}
		
		return r;
	}
	
}
