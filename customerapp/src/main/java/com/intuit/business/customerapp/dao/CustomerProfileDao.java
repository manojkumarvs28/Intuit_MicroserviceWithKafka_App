package com.intuit.business.customerapp.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.business.customerapp.domain.CustomerProfile;

@Transactional
@Repository
public interface CustomerProfileDao {
      CustomerProfile addCustomerProfile(CustomerProfile profile);
      
      CustomerProfile getCustomerProfile(String customerId);
      
      CustomerProfile updateCustomerProfile(CustomerProfile profile);
      
      String deleteCustomerProfile(String customerId);
      
      CustomerProfile updateCustomerProfileStatus(CustomerProfile customerProfile);
}
