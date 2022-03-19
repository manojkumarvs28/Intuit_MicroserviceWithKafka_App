package com.intuit.business.productapp.events;

import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.intuit.business.productapp.config.CustomerProducts;
import com.intuit.business.productapp.constants.Constants;
import com.intuit.business.productapp.domain.CustomerProfile;
import com.intuit.business.productapp.domain.Product;
import com.intuit.business.productapp.service.ProducerService;
import com.intuit.business.productapp.service.ProductService;

@Component
public class EventsListener {
	
	@Autowired
	List<CustomerProducts> products;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProducerService producerService;
	
	private final Gson gson = new Gson();
	
	public List<Product> getAllProductsSubscribedByCustomer(String customerId){
		
		return productService.getProductsSubscribedByCustomerId(customerId);
	}
	
	private boolean validate(String productName, CustomerProfile customerProfile) {
		Optional<CustomerProducts> optProduct = products.stream().filter( product -> product.getType().equals(productName)).findFirst();
		if(optProduct.isPresent()) {
			CustomerProducts product = optProduct.get();
			return product.validate(customerProfile);
		}
		return false;
	}
	
//	@Async
//	@EventListener
//	void handleDeletionOfCustomers(DeleteEvent deleteEvent) {
//		String customerId = deleteEvent.getCustomerId(); 
//		redisUtility.deleteCachedValue(Constants.CUSTOM_PROFILE, customerId);
//	}
	
	
	@Async
	@EventListener
	 void handleValidationEvents(ValidationEvent validationEvent) {
		CustomerProfile customerProfile = validationEvent.getCustomerProfile(); 
		String customerId= customerProfile.getPanNo().toString();
		List<Product> products = getAllProductsSubscribedByCustomer(customerId);
		boolean isValid = true;
		for(Product product: products) {
			boolean validationRes= validate(product.getProductName(), customerProfile);
			if(!validationRes) {
				customerProfile.setStatus(Constants.REJECTED);
				//customerProfileService.updateCustomerProfileStatus(customerProfile); 
				producerService.send(customerProfile);
				isValid= false;
				break; 
			}
		}
		if(isValid) {
			customerProfile.setStatus(Constants.ACCEPTED);
			//customerProfileService.updateCustomerProfileStatus(customerProfile);
			producerService.send(customerProfile);
			String json = gson.toJson(customerProfile);
			//redisUtility.addToCache(Constants.CUSTOM_PROFILE, customerId, json);
		}
	}
	
}
