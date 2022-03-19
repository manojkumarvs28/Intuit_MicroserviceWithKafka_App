package com.intuit.business.customerapp.config.impl;

import org.springframework.stereotype.Service;

import com.intuit.business.customerapp.config.CustomerProducts;
import com.intuit.business.customerapp.domain.CustomerProfile;

@Service
public class Tsheets implements CustomerProducts {
	
	private final String type="Tsheets";
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean validate(CustomerProfile customerProfile) {
		
		return true;

	}

}
