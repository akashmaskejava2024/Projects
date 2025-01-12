package com.nt.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> categoryNotfoundExceptionHandler(CategoryNotFoundException e) {
		return ResponseEntity.status(e.getStatus()).body(e.getMessage());

	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotfoundExceptionHandler(ProductNotFoundException e) {
		return ResponseEntity.status(e.getStatus()).body(e.getMessage());

	}

}
