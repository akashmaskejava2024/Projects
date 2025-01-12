package com.nt.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
// to avoid the use of parent class fields in the equality and hashcode genearation

public class ProductNotFoundException extends Exception {

	private String message;
	private HttpStatus status;
}
