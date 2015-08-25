package com.amb.mm.travel.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.web.BusOrderController;
import com.amb.mm.travel.core.POI;
import com.amb.mm.travel.core.service.POIRepository;

@Controller
public class GreetingController {
	@Autowired
    private POIRepository poiRepo;
	
	@Autowired
	private BusOrderController busOrderCtrl;
	
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("search", new Search());
        return "index";
    }
    
    @ModelAttribute("busCities")
    public Iterable<POI> populateTypes() {
        return this.poiRepo.findAll();
    }
    

    @RequestMapping(value = "/search", method = RequestMethod.POST)
	public String doSearch(@ModelAttribute("search") Search search, Model model)
	{
		//model.addAttribute("search", search);
		
		// TODO: based on the search type, results would be different, redirect to different page
		model.addAttribute("busOffers", this.getBusOffers(search));
		
		return "bus-search-results";
	}
    
    private List<BusOffer> getBusOffers(Search search)
	{
    	Assert.notNull(search, "Search must not be null");
    	Assert.notNull(search.fromId, "fromId must not be null");
    	Assert.notNull(search.toId, "fromId must not be null");
    	
  
    	List<BusOffer> results = new ArrayList<BusOffer>();
    	
    	results = busOrderCtrl.listBusOffersByRoute(search.fromId, search.toId, new Date());
    	
		return results;
	}
    
    
}
