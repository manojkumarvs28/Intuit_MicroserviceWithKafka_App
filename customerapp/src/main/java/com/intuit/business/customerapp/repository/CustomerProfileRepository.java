package com.intuit.business.customerapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.intuit.business.customerapp.domain.CustomerProfile;

@Transactional
@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, UUID> {
	@Query(value = "Select * from INTUIT.customer_profile where pan_no = :customerId", nativeQuery = true)
	CustomerProfile getCustomerProfileByCustomerId(@Param("customerId") String customerId);
}
