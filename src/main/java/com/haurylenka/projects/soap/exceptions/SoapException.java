package com.haurylenka.projects.soap.exceptions;

public class SoapException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoapException() {
		super();
	}

	public SoapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SoapException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoapException(String message) {
		super(message);
	}

	public SoapException(Throwable cause) {
		super(cause);
	}

}
