package com.intuit.business.subscriptionapp.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.intuit.business.subscriptionapp.constants.ErrorMessageConstants;
import com.intuit.business.subscriptionapp.dao.SubscriptionDao;
import com.intuit.business.subscriptionapp.domain.Subscription;
import com.intuit.business.subscriptionapp.exceptions.SubscriptionException;
import com.intuit.business.subscriptionapp.repository.SubscriptionRepository;

@Service
public class SubscriptionDaoImpl implements SubscriptionDao{
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	private static Logger logger= LoggerFactory.getLogger(SubscriptionDaoImpl.class);

	@Override
	public List<Subscription> getSubscriptionsForCustomer(String customerId) {
		// TODO Auto-generated method stub
		List<Subscription> list=null;
		try {
			list= subscriptionRepository.getSubsciptionsByCustomerId(customerId);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_RETRIEVING_SUBSCRIPTIONS_FOR_CUSTOMER+ex.getMessage());
			throw new SubscriptionException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURED_WHILE_RETRIEVING_SUBSCRIPTIONS_FOR_CUSTOMER, HttpStatus.BAD_REQUEST, "getSubscriptionsForCustomer");
		}
		return list;
	}

	@Override
	public Subscription addSubscription(String customerId, String productId) {
		try {
			UUID subscriptionId = UUID.randomUUID();
			Subscription subscription = new Subscription();
			subscription.setSubscriptionId(subscriptionId);
			subscription.setCustomerId(UUID.fromString(customerId));
			subscription.setProductId(UUID.fromString(productId));
			
			return subscriptionRepository.save(subscription);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_ADDING_NEW_SUBSCRIPTION_FOR_CUSTOMER+ex.getMessage());
			throw new SubscriptionException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURED_WHILE_ADDING_NEW_SUBSCRIPTION_FOR_CUSTOMER, HttpStatus.BAD_REQUEST, "addSubscription");
		}
	}

	@Override
	public String deleteSubscription(String subscriptionId) {
		try {
		subscriptionRepository.deleteById(UUID.fromString(subscriptionId));
		return "Entity Deleted SuccessFully";
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_DELETING_SUBSCRIPTION_FOR_CUSTOMER+ex.getMessage());
			throw new SubscriptionException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURED_WHILE_DELETING_SUBSCRIPTION_FOR_CUSTOMER, HttpStatus.BAD_REQUEST, "deleteSubscription");
		}
	}

	@Override
	public Subscription updateSubscription(String subscriptionId, String cutomerId, String productId) {
		try {
			UUID subscriptionUuid = UUID.fromString(subscriptionId);
			Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(subscriptionUuid);
			Subscription subscription = null;
			if(!subscriptionOpt.isPresent()) {
				subscription = new Subscription();
			}else {
				subscription = subscriptionOpt.get();
			}
			subscription.setProductId(UUID.fromString(productId));
			subscription.setCustomerId(UUID.fromString(cutomerId));
			subscription.setSubscriptionId(subscriptionUuid);
			
			return subscriptionRepository.save(subscription);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_UPDATING_SUBSCRIPTION_FOR_CUSTOMER+ex.getMessage());
			throw new SubscriptionException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURED_WHILE_UPDATING_SUBSCRIPTION_FOR_CUSTOMER, HttpStatus.BAD_REQUEST, "updateSubscription");
		}
	}

}
