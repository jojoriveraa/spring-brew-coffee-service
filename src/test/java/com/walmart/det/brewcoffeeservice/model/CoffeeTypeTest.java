package com.walmart.det.brewcoffeeservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CoffeeTypeTest {
	
	private CoffeeType coffeeType;

	private static final int EXPECTED_ID = 0;
	private static final String EXPECTED_NAME = "dummy";
	private static final double EXPECTED_PRICE = 15.50;
	private static final int EXPECTED_BEANS = 70;
	private static final int EXPECTED_MILK = 100;

	@Before
	public void setup() {
		coffeeType = new CoffeeType(EXPECTED_ID, EXPECTED_NAME, EXPECTED_PRICE, EXPECTED_BEANS, EXPECTED_MILK);
	}

	@Test
	public void coffeeTypeCreationTest() {
		assertThat(coffeeType).isNotNull();
	}

	@Test
	public void getIdTest (){
		assertThat(coffeeType.getId()).isEqualTo(0);
	}

	@Test
	public void getNameTest (){
		assertThat(coffeeType.getName()).isEqualTo(EXPECTED_NAME);
	}

	@Test
	public void getPriceTest (){
		assertThat(coffeeType.getPrice()).isEqualTo(EXPECTED_PRICE);
	}

	@Test
	public void getRequiredBeansTest (){
		assertThat(coffeeType.getRequiredBeans()).isEqualTo(EXPECTED_BEANS);
	}

	@Test
	public void getRequiredMilkTest (){
		assertThat(coffeeType.getRequiredMilk()).isEqualTo(EXPECTED_MILK);
	}
	
	@Test
	public void toStringTest (){
		assertThat(coffeeType.toString()).isEqualTo("CoffeeType [id=0, name=dummy, price=15.5, requiredBeans=70, requiredMilk=100]");
	}

}
