package com.haurylenka.projects.soap.exceptions;

public class SoapImplLoadException extends Exception {

	private static final long serialVersionUID = 1L;

	public SoapImplLoadException() {
		super();
	}

	public SoapImplLoadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SoapImplLoadException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoapImplLoadException(String message) {
		super(message);
	}

	public SoapImplLoadException(Throwable cause) {
		super(cause);
	}

}
