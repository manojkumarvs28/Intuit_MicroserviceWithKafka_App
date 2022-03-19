package com.intuit.business.customerapp.events;

import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.intuit.business.customerapp.config.CustomerProducts;
import com.intuit.business.customerapp.constants.Constants;
import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.service.CustomerProfileService;
import com.intuit.business.customerapp.utilities.RedisUtility;

@Component
public class EventsListener {
	
	@Autowired
	List<CustomerProducts> products;

	
	@Autowired
	CustomerProfileService customerProfileService;
	
	@Autowired
	RedisUtility redisUtility;
	
	private final Gson gson = new Gson();
//	
//	public List<Product> getAllProductsSubscribedByCustomer(String customerId){
//		
//		return productService.getProductsSubscribedByCustomerId(customerId);
//	}
	
	private boolean validate(String productName, CustomerProfile customerProfile) {
		Optional<CustomerProducts> optProduct = products.stream().filter( product -> product.getType().equals(productName)).findFirst();
		if(optProduct.isPresent()) {
			CustomerProducts product = optProduct.get();
			return product.validate(customerProfile);
		}
		return false;
	}
	
	@Async
	@EventListener
	void handleDeletionOfCustomers(DeleteEvent deleteEvent) {
		String customerId = deleteEvent.getCustomerId(); 
		redisUtility.deleteCachedValue(Constants.CUSTOM_PROFILE, customerId);
	}
	
	
	@Async
	@EventListener
	 void handleValidationEvents(ValidationEvent validationEvent) {
		
		CustomerProfile customerProfile = validationEvent.getCustomerProfile(); 
		customerProfileService.updateCustomerProfileStatus(customerProfile);
		String customerId = customerProfile.getPanNo().toString();
		if(customerProfile.getStatus().equals(Constants.ACCEPTED)) {
			String json = gson.toJson(customerProfile);
			redisUtility.addToCache(Constants.CUSTOM_PROFILE, customerId, json);
		}
//		else {
//			redisUtility.deleteCachedValue(Constants.CUSTOM_PROFILE, customerId);
//		}
	
//		String customerId= customerProfile.getPanNo().toString();
//		List<Product> products = getAllProductsSubscribedByCustomer(customerId);
//		boolean isValid = true;
//		for(Product product: products) {
//			boolean validationRes= validate(product.getProductName(), customerProfile);
//			if(!validationRes) {
//				customerProfile.setStatus(Constants.REJECTED);
//				customerProfileService.updateCustomerProfileStatus(customerProfile); 
//				isValid= false;
//				break; 
//			}
//		}
//		if(isValid) {
//			customerProfile.setStatus(Constants.ACCEPTED);
//			customerProfileService.updateCustomerProfileStatus(customerProfile);
//			String json = gson.toJson(customerProfile);
//			redisUtility.addToCache(Constants.CUSTOM_PROFILE, customerId, json);
//		}
	}
	
}
