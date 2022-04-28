package com.market.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyGlobalExceptionHandler {

	private static final String duplicateCompanyRegistrationMessage = "The Company you are trying to register already exists in the system.Please try registration with different company code";

	private static final String companyNotFoundMessage = "The Company you are trying to search doesnt exists in the system";

	@ExceptionHandler
	public ResponseEntity<Object> customerExceptionHandler(CustomRuntimeException customRuntimeException) {
		return new ResponseEntity<>(customRuntimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<Object> duplicateCompanyRegistrationExceptionHandler(
			DuplicateCompanyRegistrationException duplicateCompanyRegistrationException) {
		return new ResponseEntity<>(duplicateCompanyRegistrationMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<Object> companyNotFoundExceptionHandler(CompanyNotFoundException companyNotFoundException) {
		return new ResponseEntity<>(companyNotFoundMessage, HttpStatus.NOT_FOUND);
	}

}
