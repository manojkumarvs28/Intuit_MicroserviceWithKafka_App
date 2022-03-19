package com.intuit.business.productapp.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.intuit.business.productapp.constants.Constants;
import com.intuit.business.productapp.constants.ErrorMessageConstants;
import com.intuit.business.productapp.dao.ProductDao;
import com.intuit.business.productapp.domain.Product;
import com.intuit.business.productapp.exceptions.ProductException;
import com.intuit.business.productapp.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	private static Logger logger =  LoggerFactory.getLogger(ProductDaoImpl.class);
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		List<Product> products= null;
		try {
			products = productRepository.findAll();
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_RETRIEVING_PRODUCTS, ex.getMessage());
			throw new ProductException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURRED_WHILE_RETRIEVING_PRODUCTS , HttpStatus.BAD_REQUEST, "getProducts");
		}
		return products;
	}

	@Override
	public Product addProduct(String productName) {
		Product product = null;
		try {
			product = new Product();
			UUID productId = UUID.randomUUID();
			product.setProductId(productId);
			product.setProductName(productName);
			product=  productRepository.save(product);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_ADDING_NEW_PRODUCT, ex.getMessage());
			throw new ProductException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURRED_WHILE_ADDING_NEW_PRODUCT , HttpStatus.BAD_REQUEST, "addProduct");
		}
		return product;
	}

	@Override
	public Product updateProduct(String productId, String productName) {
		Product product=null;
		try {
			UUID productUuid = UUID.fromString(productId);
			Optional<Product> productOpt = productRepository.findById(productUuid);
			if(!productOpt.isPresent()) {
				product = new Product();
			}else {
				product = productOpt.get();
			}
			product.setProductId(productUuid);
			product.setProductName(productName);
			
			product= productRepository.save(product);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_UPDATING_PRODUCT, ex.getMessage());
			throw new ProductException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURRED_WHILE_UPDATING_PRODUCT , HttpStatus.BAD_REQUEST, "updateProduct");
		}
		return product;
	}

	@Override
	public String deleteProduct(String productId) {
		String res= "";
		try {
			UUID productUuid = UUID.fromString(productId);
			 productRepository.deleteById(productUuid);
			 res =  Constants.PRODUCT_DELETED_SUCCESSFULLY;
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_DELETING_PRODUCT, ex.getMessage());
			throw new ProductException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURRED_WHILE_DELETING_PRODUCT , HttpStatus.BAD_REQUEST, "deleteProduct");
		}
		return res;
	}

	@Override
	public List<Product> getProductsSubscribedByCustomerId(String customerId) {
		List<Product> res=null;
		try {
			res = productRepository.getProductsSubscribedByCustomerId(customerId);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_ADDING_CUSTOMER_PROFILE, ex.getMessage());
			throw new ProductException(ex.getMessage(), ErrorMessageConstants.ERROR_OCCURRED_WHILE_ADDING_CUSTOMER_PROFILE , HttpStatus.BAD_REQUEST, "getProductsSubscribedByCustomerId");
		}
		return res;
	}

}
