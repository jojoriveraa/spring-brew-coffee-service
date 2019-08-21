package com.walmart.det.brewcoffeeservice.exceptions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class CoffeTypeNotImplementedExceptionTest {

	private String type;

	@Test
	public void coffeTypeNotImplementedException_Basic() {
		assertThatThrownBy(() -> {
			throw new CoffeTypeNotImplementedException();
		}).hasMessage("Selected coffeetype has not been implemented");
	}

	@Test
	public void coffeTypeNotImplementedException_WithCoffeeType() {
		type = "Dummy Type";
		assertThatThrownBy(() -> {
			throw new CoffeTypeNotImplementedException(type);
		}).hasMessage("Coffeetype: [" + type + "] has not been implemented");
	}

	@Test
	public void coffeTypeNotImplementedException_WithCoffeeTypeAndException() {
		type = "Dummy Type";
		assertThatThrownBy(() -> {
			throw new CoffeTypeNotImplementedException(type, new IllegalArgumentException());
		}).hasMessage("Coffeetype: [" + type + "] has not been implemented")
				.hasCauseExactlyInstanceOf(IllegalArgumentException.class);
	}

}
