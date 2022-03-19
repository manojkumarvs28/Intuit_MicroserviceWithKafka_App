package com.intuit.business.customerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.service.CustomerProfileService;

@RestController
@RequestMapping("/customerProfile")
public class CustomerProfileController {
	
	@Autowired
	CustomerProfileService customerProfileService;
	
//	@Autowired
//	RestTemplate restTemplate;

	@GetMapping()
	public CustomerProfile getCustomerProfile(@RequestParam String customerId) {
		//restTemplate.getForObject("https://productapp/products", Product.class);
		return customerProfileService.getCustomerProfile(customerId);
	}
	
	//@PutMapping(value="/updateCustomerProfile", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerProfile updateCustomerProfile(@RequestBody CustomerProfile customerProfile) {
		return customerProfileService.updateCustomerProfile(customerProfile);
	}
	
//	@PostMapping( value="/createCustomerProfile",
//			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PostMapping(
	consumes = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerProfile createCustomerProfile(@RequestBody CustomerProfile customerProfile) {
		
		return customerProfileService.createCustomerProfile(customerProfile);
	}
	
	//@DeleteMapping("/deleteCustomerProfile")
	@DeleteMapping()
	public String deleteCustomerProfile(@RequestParam String customerId) {
		return customerProfileService.deleteCustomerProfile(customerId);
	}
	
}
