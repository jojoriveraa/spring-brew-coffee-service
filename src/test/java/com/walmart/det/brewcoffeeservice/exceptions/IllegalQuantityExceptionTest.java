package com.walmart.det.brewcoffeeservice.exceptions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class IllegalQuantityExceptionTest {

	private int quantity;

	@Test
	public void coffeTypeNotImplementedException_Basic() {
		assertThatThrownBy(() -> {
			throw new IllegalQuantityException();
		}).hasMessage("Can not brew zero or negative numbers");
	}

	@Test
	public void coffeTypeNotImplementedException_WithCoffeeType() {
		int quantity = 5;
		assertThatThrownBy(() -> {
			throw new IllegalQuantityException(quantity);
		}).hasMessage("Can not brew zero or negative numbers. Selected: [" + quantity + "]");
	}

	@Test
	public void coffeTypeNotImplementedException_WithCoffeeTypeAndException() {
		quantity = 5;
		assertThatThrownBy(() -> {
			throw new IllegalQuantityException(quantity, new IllegalArgumentException());
		}).hasMessage("Can not brew zero or negative numbers. Selected: [" + quantity + "]")
				.hasCauseExactlyInstanceOf(IllegalArgumentException.class);
	}

}
