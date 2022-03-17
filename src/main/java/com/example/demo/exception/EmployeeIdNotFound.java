package com.example.demo.exception;

public class EmployeeIdNotFound extends RuntimeException {

	private String message;
	
	public EmployeeIdNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	
}
