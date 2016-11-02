package org.willian.firstjerseyproject.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3130543478221275093L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
