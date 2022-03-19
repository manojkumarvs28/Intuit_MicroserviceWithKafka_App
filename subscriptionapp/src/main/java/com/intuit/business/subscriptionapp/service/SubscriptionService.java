package com.intuit.business.subscriptionapp.service;

import java.util.List;

import com.intuit.business.subscriptionapp.domain.Subscription;

public interface SubscriptionService {
	
		List<Subscription> getSubscriptionsForCustomer(String customerId);
		
		Subscription addSubscription(String customerId, String productId);
		
		String deleteSubscription(String subscriptionId);
		
	    Subscription updateSubscription(String subscription, String cutomerId, String productId);
	    
}
