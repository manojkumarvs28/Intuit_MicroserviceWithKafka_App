package com.intuit.business.productapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.business.productapp.domain.Product;

@Transactional
@Repository
public interface ProductDao {
   List<Product> getProducts();
   
   Product addProduct(String product);
   
   Product updateProduct(String productId, String productName);
   
   String deleteProduct(String productId);
   
   List<Product> getProductsSubscribedByCustomerId(String customerId);
}
