package com.intuit.business.subscriptionapp.exceptions;

import java.time.LocalDateTime;

public class CustomExceptionSchema {
	private static final long serialVersionUID = 1L;
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private String functionIdentifier;

	public CustomExceptionSchema(String message, String details, String functionIdentifier) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
		this.functionIdentifier = functionIdentifier;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getFunctionIdentifier() {
		return functionIdentifier;
	}

	public void setFunctionIdentifier(String functionIdentifier) {
		this.functionIdentifier = functionIdentifier;
	}

}
