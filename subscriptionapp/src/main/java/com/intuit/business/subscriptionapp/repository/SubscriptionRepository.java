package com.intuit.business.subscriptionapp.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.business.subscriptionapp.domain.Subscription;

@Transactional
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
	@Query(value = "Select * from INTUIT.subscription where customer_id = :customerId", nativeQuery = true)
	List<Subscription> getSubsciptionsByCustomerId(@Param("customerId") String customerId);
}
