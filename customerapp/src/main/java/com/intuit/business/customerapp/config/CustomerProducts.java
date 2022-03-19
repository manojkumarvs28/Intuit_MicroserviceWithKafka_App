package com.intuit.business.customerapp.config;

import com.intuit.business.customerapp.domain.CustomerProfile;

public interface CustomerProducts {
   public String getType();	
	
   public boolean validate(CustomerProfile customerProfile);
}
