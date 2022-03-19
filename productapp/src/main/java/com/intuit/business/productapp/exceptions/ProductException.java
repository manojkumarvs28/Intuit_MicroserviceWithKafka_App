package com.intuit.business.productapp.exceptions;

import org.springframework.http.HttpStatus;

public class ProductException extends CustomException {

	public ProductException(String message, String details, HttpStatus statusCode, String functionIdentifier) {
		super(message, details, statusCode, functionIdentifier);
	}

}
