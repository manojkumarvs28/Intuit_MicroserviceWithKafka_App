package com.intuit.business.subscriptionapp.exceptions;

import org.springframework.http.HttpStatus;

public class SubscriptionException extends CustomException {

	public SubscriptionException(String message, String details, HttpStatus statusCode, String functionIdentifier) {
		super(message, details, statusCode, functionIdentifier);
	}

}
