package com.market.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyGlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> customerExceptionHandler(CustomRuntimeException customRuntimeException) {
		return new ResponseEntity<>(customRuntimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
