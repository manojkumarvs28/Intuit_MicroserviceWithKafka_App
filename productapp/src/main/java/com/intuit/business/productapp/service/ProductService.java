package com.intuit.business.productapp.service;

import java.util.List;

import com.intuit.business.productapp.domain.Product;

public interface ProductService {
	
	List<Product> getProduct();
	
	Product addProduct(String productName);
	
	Product updateProduct(String productId, String productName);
	
	String deleteProduct(String productId);
	
	List<Product> getProductsSubscribedByCustomerId(String customerId);

}
