package com.intuit.business.customerapp.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerProfileException extends CustomException {

	public CustomerProfileException(String message, String details, HttpStatus statusCode, String functionIdentifier) {
		super(message, details, statusCode, functionIdentifier);
	}

}
