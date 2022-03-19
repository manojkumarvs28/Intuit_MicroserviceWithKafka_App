package com.intuit.business.customerapp.domain;

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
@Table(name="CustomerProfile",  schema = "INTUIT")
public class CustomerProfile {
  
	@Id
	@Type(type="uuid-char")
	@Column(name="panNo")
	private UUID panNo;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="legalName")
	private String legalName;
	
	@Column(name="legalAddress")
	private String legalAddress;
	
	@Column(name="businessAddress")
	private String businessAddress;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="website")
	private String website;
	
	@Column(name="status")
	private String status;
	
	 @Override
	  public String toString() {
	    return "CustomerProfile [panNo=" + panNo + ", companyName=" + companyName 
	    		+ ", legalName=" + legalName + ", legalAddress=" +legalAddress+", businessAddress="+businessAddress
	    		+", emailId="+emailId+", website="+website+", status="+status+"]";
	  }
	
}
