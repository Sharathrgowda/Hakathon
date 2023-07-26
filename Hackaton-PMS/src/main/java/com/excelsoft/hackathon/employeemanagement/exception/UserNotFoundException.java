package com.excelsoft.hackathon.employeemanagement.exception;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Exception exception) {
		super(message, exception);

	}

}
