package com.intuit.business.subscriptionapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intuit.business.subscriptionapp.exceptions.CustomExceptionSchema;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(SubscriptionException.class)
	public final ResponseEntity<CustomExceptionSchema> handleCustomExceptions(SubscriptionException ex){
		CustomExceptionSchema exceptionResponse = new CustomExceptionSchema(ex.getMessage(), ex.getDetails(),
				ex.getFunctionIdentifier());
		return new ResponseEntity(exceptionResponse, ex.getStatusCode());
	}

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<CustomExceptionSchema> handleCustomExceptions(CustomException ex){
		CustomExceptionSchema exceptionResponse = new CustomExceptionSchema(ex.getMessage(), ex.getDetails(),
				ex.getFunctionIdentifier());
		return new ResponseEntity(exceptionResponse, ex.getStatusCode());
	}
	
	
}
