package com.intuit.business.subscriptionapp.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="Subscription", schema = "INTUIT")
public class Subscription {

	@Id
	@Type(type="uuid-char")
	@Column(name="subscriptionId")
	UUID subscriptionId;
	
	@Type(type="uuid-char")
	@Column(name="customerId")
	UUID customerId;
	
	@Type(type="uuid-char")
	@Column(name="productId")
	UUID productId;
	
	
}
