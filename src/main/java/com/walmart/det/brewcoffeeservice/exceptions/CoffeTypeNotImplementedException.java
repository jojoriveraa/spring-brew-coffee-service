package com.walmart.det.brewcoffeeservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CoffeTypeNotImplementedException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public CoffeTypeNotImplementedException() {
		super("Selected coffeetype has not been implemented");
	}

	public CoffeTypeNotImplementedException(String type) {
		super("Coffeetype: [" + type + "] has not been implemented");
	}

	public CoffeTypeNotImplementedException(String type, IllegalArgumentException e) {
		super("Coffeetype: [" + type + "] has not been implemented", e);
	}
}