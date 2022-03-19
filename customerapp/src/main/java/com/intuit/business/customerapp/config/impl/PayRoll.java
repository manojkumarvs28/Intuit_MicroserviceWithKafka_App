package com.intuit.business.customerapp.config.impl;

import org.springframework.stereotype.Service;

import com.intuit.business.customerapp.config.CustomerProducts;
import com.intuit.business.customerapp.domain.CustomerProfile;

@Service
public class PayRoll implements CustomerProducts {
	
	private final String type="PayRoll";
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean validate(CustomerProfile customerProfile) {
		
		return true;
		
	}

}
