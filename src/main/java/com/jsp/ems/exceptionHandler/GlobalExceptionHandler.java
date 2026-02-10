package com.jsp.ems.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ems.exception.EmployeeNotFoundException;
import com.jsp.ems.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmployeeNotFound(EmployeeNotFoundException exception) {
		
		ResponseStructure<String> responseStructure = new ResponseStructure<String>(HttpStatus.NOT_FOUND.value(), "Resource Not Found", exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}
}
