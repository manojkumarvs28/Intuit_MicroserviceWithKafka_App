package com.intuit.business.subscriptionapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.business.subscriptionapp.dao.SubscriptionDao;
import com.intuit.business.subscriptionapp.domain.Subscription;
import com.intuit.business.subscriptionapp.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
	
	@Autowired
	SubscriptionDao subscriptionDao;

	@Override
	public List<Subscription> getSubscriptionsForCustomer(String customerId) {
		// TODO Auto-generated method stub
		return subscriptionDao.getSubscriptionsForCustomer(customerId);
	}

	@Override
	public Subscription addSubscription(String customerId, String productId) {
		// TODO Auto-generated method stub
		return subscriptionDao.addSubscription(customerId, productId);
	}

	@Override
	public String deleteSubscription(String subscriptionId) {
		// TODO Auto-generated method stub
		return subscriptionDao.deleteSubscription(subscriptionId);
	}

	@Override
	public Subscription updateSubscription(String subscription, String cutomerId, String productId) {
		// TODO Auto-generated method stub
		return subscriptionDao.updateSubscription(subscription, cutomerId, productId);
	}

}
