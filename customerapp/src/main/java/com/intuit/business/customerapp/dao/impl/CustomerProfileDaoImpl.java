package com.intuit.business.customerapp.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.intuit.business.customerapp.config.SharedService;
import com.intuit.business.customerapp.constants.Constants;
import com.intuit.business.customerapp.constants.ErrorMessageConstants;
import com.intuit.business.customerapp.dao.CustomerProfileDao;
import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.events.DeleteEvent;
import com.intuit.business.customerapp.exceptions.CustomerProfileException;
import com.intuit.business.customerapp.repository.CustomerProfileRepository;
import com.intuit.business.customerapp.service.ProducerService;


@Repository
public class CustomerProfileDaoImpl implements CustomerProfileDao{
	
	private static Logger logger =  LoggerFactory.getLogger(CustomerProfileDaoImpl.class);
	
	@Autowired
	CustomerProfileRepository customerProfileRepository;
	
	@Autowired
	SharedService sharedService;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	ProducerService producerService;

	@Override
	public CustomerProfile addCustomerProfile(CustomerProfile profile) {
		CustomerProfile customerProfile = null;
		try {
			profile.setStatus(Constants.IN_PROGRESS);
			customerProfile =  customerProfileRepository.save(profile);
			//publisher.publishEvent(new ValidationEvent(this, customerProfile));
			//else
			//publish to kafka
			producerService.send(customerProfile);
		
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_ADDING_CUSTOMER_PROFILE, ex.getMessage());
			throw new CustomerProfileException(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST, "addCustomerProfile");
		}
		return customerProfile;
	}

	@Override
	public CustomerProfile getCustomerProfile(String customerId) {
		CustomerProfile profile= null;
		try {
			profile= customerProfileRepository.getCustomerProfileByCustomerId(customerId);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_RETRIEVING_CUSTOMER_PROFILE, ex.getMessage());
			throw new CustomerProfileException(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST, "getCustomerProfile");
		}
		return profile;
	}

	@Override
	public CustomerProfile updateCustomerProfile(CustomerProfile updatedCustomerProfile) {
		CustomerProfile profile= null;
		try {
			UUID customerId = (updatedCustomerProfile.getPanNo());
			CustomerProfile existingCustomerProfileprofile = customerProfileRepository.getCustomerProfileByCustomerId(customerId.toString());
			
			if(null!=existingCustomerProfileprofile) {
				producerService.send(updatedCustomerProfile);
				updatedCustomerProfile.setStatus(existingCustomerProfileprofile.getStatus());
				return customerProfileRepository.save(updatedCustomerProfile);
			}
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_UPDATING_CUSTOMER_PROFILE, ex.getMessage());
			throw new CustomerProfileException(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST, "updateCustomerProfile");
		}
		 return profile;
		
	} 

	@Override
	public String deleteCustomerProfile(String customerId) {
		try {
			 customerProfileRepository.deleteById(UUID.fromString(customerId));
			 //publish an event to delete from cache
			 publisher.publishEvent(new DeleteEvent(this, customerId));
			 
			 return Constants.CUSTOMER_PROFILE_DELETION_MSG;
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_DELETING_CUSTOMER_PROFILE, ex.getMessage());
			throw new CustomerProfileException(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST, "deleteCustomerProfile");
		}
	}
	
	@Override
	public CustomerProfile updateCustomerProfileStatus(CustomerProfile customerProfile) {
		CustomerProfile profile= null;
		try {
			profile = customerProfileRepository.save(customerProfile);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_UPDATING_CUSTOMER_PROFILE_STATUS, ex.getMessage());
			throw new CustomerProfileException(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST, "updateCustomerProfileStatus");
		}
		return profile;
	}

}
