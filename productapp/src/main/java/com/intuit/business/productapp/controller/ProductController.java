package com.intuit.business.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.intuit.business.productapp.domain.CustomerProfile;
import com.intuit.business.productapp.domain.Product;
import com.intuit.business.productapp.service.ProducerService;
import com.intuit.business.productapp.service.ProductService;

@RestController
@RequestMapping
public class ProductController {
     
	@Autowired
	ProductService productService; 
	
	@Autowired
	ProducerService producerService;
	
	@GetMapping("/products")
	@ResponseBody
	public List<Product> getProducts(){
		return productService.getProduct();
	}
	
	@PostMapping("/addproduct")
	@ResponseBody
	public Product addProduct(@RequestParam String productName) {
		return productService.addProduct(productName);
	}
	
	@DeleteMapping("/deleteProduct")
	@ResponseBody
	public String deleteProduct(@RequestParam String productId) {
		return productService.deleteProduct(productId);
	}
	
	@PutMapping("/updateProduct")
	@ResponseBody
	public Product updateProduct(@RequestParam String productId, @RequestParam String productName) {
		return productService.updateProduct(productId, productName);
	}
	
//	@PostMapping( value="/createCustomerProfile",
//			consumes = {MediaType.APPLICATION_JSON_VALUE})
//	public String createCustomerProfile(@RequestBody CustomerProfile customerProfile) {
//		
//		//return customerProfileService.createCustomerProfile(customerProfile);
//		producerService.send(customerProfile);
//		return "Sent";
//	}
	
	
	
}
