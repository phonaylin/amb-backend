package com.amb.mm.travel.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@Autowired
    CustomerRepository repository;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/customers")
	public List<Customer> listCustomers() {
		return (List<Customer>) repository.findAll();
	}

}
