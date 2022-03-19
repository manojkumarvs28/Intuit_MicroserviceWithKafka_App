package com.intuit.business.customerapp.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -1L;
	private  final LocalDateTime timestamp;
	private final String message;
	private final String details;
	// default value is OK, can be customized via constructor or setter.
	private final HttpStatus statusCode;
	// implementing function/api
	private final String functionIdentifier;

	public CustomException(String message, String details, HttpStatus statusCode, String functionIdentifier) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
		this.functionIdentifier = functionIdentifier;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getFunctionIdentifier() {
		return functionIdentifier;
	}
}
