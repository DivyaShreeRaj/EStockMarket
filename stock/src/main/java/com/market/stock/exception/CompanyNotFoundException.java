package com.market.stock.exception;

public class CompanyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 241812918098965787L;

	public CompanyNotFoundException() {
		super();
	}

	public CompanyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyNotFoundException(String message) {
		super(message);
	}

}
