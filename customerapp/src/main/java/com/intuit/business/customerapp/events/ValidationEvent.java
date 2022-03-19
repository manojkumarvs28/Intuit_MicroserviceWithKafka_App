package com.intuit.business.customerapp.events;


import org.springframework.context.ApplicationEvent;
import com.intuit.business.customerapp.domain.CustomerProfile;

public class ValidationEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerProfile customerProfile;
	
	public ValidationEvent(Object source, CustomerProfile customerProfile) {
		super(source);
		this.customerProfile = customerProfile;
	}
	
	public CustomerProfile getCustomerProfile() {
		return this.customerProfile;
	}

}
