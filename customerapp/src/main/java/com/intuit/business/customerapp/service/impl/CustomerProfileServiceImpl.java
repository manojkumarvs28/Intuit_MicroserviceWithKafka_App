package com.intuit.business.customerapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.business.customerapp.constants.Constants;
import com.intuit.business.customerapp.dao.CustomerProfileDao;
import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.service.CustomerProfileService;
import com.intuit.business.customerapp.utilities.RedisUtility;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.google.gson.Gson;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService{
	
	@Autowired
	CustomerProfileDao customerProfileDao;
	
	@Autowired
	RedisUtility redisUtility;
	
	private final Gson gson = new Gson();

	@Override
	public CustomerProfile getCustomerProfile(String customerId) {
		CustomerProfile profile=  getCustomerProfileFromCache(customerId);
		if(null!=profile)
			return profile;
		return getCustomProfileUsingFallBack(customerId);
	}
	
	@HystrixCommand(fallbackMethod = "getCustomProfileUsingFallBack", 
		    commandProperties = {
				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="6"), //consider every last 6 requests for checking circuit breaker pattern to kick in
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),// if 50% of last 6 requests fail then kickin circuit breaker pattern
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="2000")// sleep window of 2sec 0r 2000 millisec for retrying anc checking if services are up 
	        },
			threadPoolKey = "cacheRetrieval",
	        threadPoolProperties = {
	                    @HystrixProperty(name = "coreSize", value = "30")// by default at one moment 10 requests can be served, so increase it to 30
	    })
	private CustomerProfile getCustomerProfileFromCache(String customerId) {
		Object profile = redisUtility.getCachedValue(Constants.CUSTOM_PROFILE, customerId);
		if(null != profile) {
			return gson.fromJson((String)profile, CustomerProfile.class);
		}
		return null;
	}
	
	public CustomerProfile getCustomProfileUsingFallBack(String customerId) {
		System.out.println("fallbackMethod "+customerId);
		return customerProfileDao.getCustomerProfile(customerId);
	}

	@Override
	public CustomerProfile updateCustomerProfile(CustomerProfile customerProfile) {
		// TODO Auto-generated method stub
		return customerProfileDao.updateCustomerProfile(customerProfile);
	}

	@Override
	public CustomerProfile createCustomerProfile(CustomerProfile customerProfile) {
		// TODO Auto-generated method stub
		return customerProfileDao.addCustomerProfile(customerProfile);
	}

	@Override
	public String deleteCustomerProfile(String customerId) {
		// TODO Auto-generated method stub
		return customerProfileDao.deleteCustomerProfile(customerId);
	}

	@Override
	public CustomerProfile updateCustomerProfileStatus(CustomerProfile customerProfile) {
		// TODO Auto-generated method stub
		return customerProfileDao.updateCustomerProfileStatus(customerProfile);
	}

}
