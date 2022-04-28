package com.market.company.exception;

public class DuplicateCompanyRegistrationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708555045975970215L;

	public DuplicateCompanyRegistrationException() {
		super();
	}

	public DuplicateCompanyRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateCompanyRegistrationException(String message) {
		super(message);
	}

}
