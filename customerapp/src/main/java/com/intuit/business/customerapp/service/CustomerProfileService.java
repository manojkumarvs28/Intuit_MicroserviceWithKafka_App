package com.intuit.business.customerapp.service;

import com.intuit.business.customerapp.domain.CustomerProfile;

public interface CustomerProfileService {

	CustomerProfile getCustomerProfile(String customerId);
	
	CustomerProfile updateCustomerProfile(CustomerProfile customerProfile);
	
	CustomerProfile createCustomerProfile(CustomerProfile customerProfile);
	
	String deleteCustomerProfile(String customerId);
	
	CustomerProfile updateCustomerProfileStatus(CustomerProfile customerProfile);
}
