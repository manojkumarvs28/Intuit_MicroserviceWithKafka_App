package com.intuit.business.customerapp.config;


import org.springframework.stereotype.Service;

import com.intuit.business.customerapp.domain.CustomerProfile;


@Service
public class SharedService {
	
	
	public CustomerProfile updateCustomProfile(CustomerProfile existingCustomerProfile, CustomerProfile updatedCustomerProfile){
		existingCustomerProfile.setBusinessAddress(updatedCustomerProfile.getBusinessAddress());
		existingCustomerProfile.setCompanyName(updatedCustomerProfile.getCompanyName());
		existingCustomerProfile.setEmailId(updatedCustomerProfile.getEmailId());
		existingCustomerProfile.setLegalAddress(updatedCustomerProfile.getLegalAddress());
		existingCustomerProfile.setLegalName(updatedCustomerProfile.getLegalName());
		existingCustomerProfile.setPanNo(updatedCustomerProfile.getPanNo());
		existingCustomerProfile.setStatus(updatedCustomerProfile.getStatus());
		existingCustomerProfile.setWebsite(updatedCustomerProfile.getWebsite());
		return existingCustomerProfile;
	}
	
}
