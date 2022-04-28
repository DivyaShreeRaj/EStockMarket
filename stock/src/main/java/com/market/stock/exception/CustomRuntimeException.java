package com.market.stock.exception;

public class CustomRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7466598140309256469L;

	public CustomRuntimeException() {
		super();
	}

	public CustomRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomRuntimeException(String message) {
		super(message);
	}

	public CustomRuntimeException(Throwable cause) {
		super(cause);
	}

}
