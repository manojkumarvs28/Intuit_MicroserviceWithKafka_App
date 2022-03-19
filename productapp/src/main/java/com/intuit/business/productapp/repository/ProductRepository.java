package com.intuit.business.productapp.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.business.productapp.domain.Product;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	@Query(value = "Select p.product_id, p.product_name from INTUIT.product p, INTUIT.subscription s where s.customer_id = :customerId and "
			+ "p.product_id=s.product_id ", nativeQuery = true)
	List<Product> getProductsSubscribedByCustomerId(@Param("customerId") String customerId);
	
//	@Query(value="Select a.report_name from irpc.tbl_other_report_details a, irpc.tbl_user_email_mapping b, irpc.tbl_report_email_mapping c where "
//			+ "b.user_id= :userId and b.email_id=c.email_id and c.report_id= a.report_id and is_active = :isActive and LOWER(report_name) LIKE %:reportName%", nativeQuery = true)
//	List<String> getReportNameSuggestions(@Param("reportName") String reportName, @Param("userId") String userId, @Param("isActive") boolean isActive);
	
}
