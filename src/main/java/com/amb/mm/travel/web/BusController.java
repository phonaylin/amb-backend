package com.amb.mm.travel.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.amb.mm.travel.domain.Bus;
import com.amb.mm.travel.domain.BusRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/buses")
public class BusController {

	@Autowired
    BusRepository repository;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Bus!";
	}
	
	@RequestMapping(value="/{operatorName}", method = RequestMethod.GET)
	public List<Bus> listBusesByOperatorName(@PathVariable String operatorName) {
		return (List<Bus>) repository.findByOperatorName(operatorName);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String add(@Valid Bus bus, BindingResult result) {
        if (result.hasErrors()) {
            return "Error saving";
        }
        repository.save(bus);
        return "redirect:/buses";
    }

}
