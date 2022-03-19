package com.intuit.business.subscriptionapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.business.subscriptionapp.domain.Subscription;

@Transactional
@Repository
public interface SubscriptionDao {

	List<Subscription> getSubscriptionsForCustomer(String customerId);
	
	Subscription addSubscription(String customerId, String productId);
	
	String deleteSubscription(String subscriptionId);
	
    Subscription updateSubscription(String subscription, String cutomerId, String productId);
	
}
