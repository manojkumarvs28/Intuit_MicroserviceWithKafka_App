package com.intuit.business.productapp.config;

import com.intuit.business.productapp.domain.CustomerProfile;

public interface CustomerProducts {
   public String getType();	
	
   public boolean validate(CustomerProfile customerProfile);
}
