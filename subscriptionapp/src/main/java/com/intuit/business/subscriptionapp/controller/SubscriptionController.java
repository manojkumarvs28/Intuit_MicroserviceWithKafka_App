package com.intuit.business.subscriptionapp.controller;

import java.util.List;

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

import com.intuit.business.subscriptionapp.domain.Subscription;
import com.intuit.business.subscriptionapp.service.SubscriptionService;

@RestController
@RequestMapping
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;
	
	@GetMapping("/subscription")
	public List<Subscription> getSubscriptionsForCustomer(@RequestParam String customerId) {
		// TODO Auto-generated method stub
		return subscriptionService.getSubscriptionsForCustomer(customerId);
	}
	
	@PostMapping("/addSubscription")
	public Subscription addSubscription(@RequestParam String customerId,@RequestParam String productId) {
		// TODO Auto-generated method stub
		return subscriptionService.addSubscription(customerId, productId);
	}

	@DeleteMapping("/removeSubscription")
	public String deleteSubscription(@RequestParam String subscriptionId) {
		// TODO Auto-generated method stub
		return subscriptionService.deleteSubscription(subscriptionId);
	}

	@PutMapping(value="/updateSubscription", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Subscription updateSubscription(@RequestBody Subscription subscription) {
		// TODO Auto-generated method stub
		 String subscriptionId = subscription.getSubscriptionId().toString();
		 String cutomerId= subscription.getCustomerId().toString(); 
		 String productId = subscription.getProductId().toString();
		return subscriptionService.updateSubscription(subscriptionId, cutomerId, productId); 
	}
	
}
