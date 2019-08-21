package com.walmart.det.brewcoffeeservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalQuantityException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public IllegalQuantityException() {
		super("Can not brew zero or negative numbers");
	}

	public IllegalQuantityException(int quantity) {
		super("Can not brew zero or negative numbers. Selected: [" + quantity + "]");
	}

	public IllegalQuantityException(int quantity, IllegalArgumentException e) {
		super("Can not brew zero or negative numbers. Selected: [" + quantity + "]", e);
	}
}