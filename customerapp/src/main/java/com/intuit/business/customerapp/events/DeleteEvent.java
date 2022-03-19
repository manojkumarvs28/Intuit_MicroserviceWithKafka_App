package com.intuit.business.customerapp.events;


import org.springframework.context.ApplicationEvent;

public class DeleteEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	
	public DeleteEvent(Object source, String id) {
		super(source);
		this.customerId = id;
	}
	
	public String getCustomerId() {
		return this.customerId;
	}

}
