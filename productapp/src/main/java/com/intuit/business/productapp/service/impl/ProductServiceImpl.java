package com.intuit.business.productapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.business.productapp.utilities.RedisUtility;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.intuit.business.productapp.constants.Constants;
import com.intuit.business.productapp.domain.CustomerProfile;
import com.intuit.business.productapp.dao.ProductDao;
import com.intuit.business.productapp.domain.Product;
import com.intuit.business.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	RedisUtility redisUtility;

	@Override
	public List<Product> getProduct() {
		// TODO Auto-generated method stub
		return productDao.getProducts();
	}
	
//	@HystrixCommand(fallbackMethod = "getProductsUsingFallBack", 
//		    commandProperties = {
//				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
//				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="6"), //consider every last 6 requests for checking circuit breaker pattern to kick in
//				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),// if 50% of last 6 requests fail then kickin circuit breaker pattern
//				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="2000")// sleep window of 2sec 0r 2000 millisec for retrying anc checking if services are up 
//	        },
//			threadPoolKey = "cacheRetrieval",
//	        threadPoolProperties = {
//	                    @HystrixProperty(name = "coreSize", value = "30")// by default at one moment 10 requests can be served, so increase it to 30
//	    })
//	private Product getCustomerProfileFromCache(String productId) {
//		Object profile = redisUtility.getCachedValue(Constants.PRODUCT, productId);
//		if(null != profile) {
//			return gson.fromJson((String)profile, Product.class);
//		}
//		return null;
//	}
//	
//	public Product getProductsUsingFallBack(String productId) {
//		System.out.println("fallbackMethod "+customerId);
//		return productDao.getProducts();
//	}
	
	//public Product getIndividualProduct(String productId) {
		// TODO Auto-generated method stub
		//return productDao.getProducts(productId);
	//}

	@Override
	public Product addProduct(String productName) {
		// TODO Auto-generated method stub
		return productDao.addProduct(productName);
	}

	@Override
	public Product updateProduct(String productId, String productName) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(productId, productName);
	}

	@Override
	public String deleteProduct(String productId) {
		// TODO Auto-generated method stub
		return productDao.deleteProduct(productId);
	}

	@Override
	public List<Product> getProductsSubscribedByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return productDao.getProductsSubscribedByCustomerId(customerId);
	}

}
