package com.intuit.business.productapp.domain;

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
@Table(name="Product",  schema = "INTUIT")
public class Product {

	
	@Id
	@Type(type="uuid-char")
	@Column(name="productId")
	private UUID productId;
	
	@Column(name="productName")
	private String productName;
	
}
