package com.intuit.business.productapp.config.impl;

import org.springframework.stereotype.Service;

import com.intuit.business.productapp.config.CustomerProducts;
import com.intuit.business.productapp.domain.CustomerProfile;


@Service
public class Accounting implements CustomerProducts{
	
	private final String type="Accounting";
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean validate(CustomerProfile customerProfile) {
		
		return true;
	}

}
