package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeIdNotFoundException {

	@ExceptionHandler(EmployeeIdNotFound.class)
	public ResponseEntity<String> employeeIdNotFoundException(EmployeeIdNotFound employeeIdNotFound){
		return new ResponseEntity<String>(employeeIdNotFound.getMessage(),HttpStatus.NOT_FOUND);
	}
}
