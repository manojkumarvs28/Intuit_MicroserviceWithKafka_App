package com.intuit.business.productapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intuit.business.productapp.exceptions.CustomExceptionSchema;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductException.class)
	public final ResponseEntity<CustomExceptionSchema> handleCustomExceptions(ProductException ex){
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
