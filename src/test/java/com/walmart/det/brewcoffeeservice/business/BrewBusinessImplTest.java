package com.walmart.det.brewcoffeeservice.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.Before;
import org.junit.Test;

import com.walmart.det.brewcoffeeservice.model.CoffeeTypeEnum;
import com.walmart.det.brewcoffeeservice.service.BrewBusiness;
import com.walmart.det.brewcoffeeservice.service.BrewBusinessImpl;

public class BrewBusinessImplTest {

	private BrewBusiness businessImpl;

	@Before
	public void setup() {
		businessImpl = new BrewBusinessImpl();
	}

	@Test
	public void brew_basic() {
		assertThat(businessImpl.brew(CoffeeTypeEnum.ESPRESSO))
				.allMatch(x -> x.getType().equals(CoffeeTypeEnum.ESPRESSO));
	}

	@Test
	public void brew_OneValue() {
		assertThat(businessImpl.brew(CoffeeTypeEnum.ESPRESSO, 1))
				.allMatch(x -> x.getType().equals(CoffeeTypeEnum.ESPRESSO));
	}

	@Test
	public void brew_SomeValues() {
		assertThat(businessImpl.brew(CoffeeTypeEnum.ESPRESSO, 3)).hasSize(3)
				.allMatch(x -> x.getType().equals(CoffeeTypeEnum.ESPRESSO));

	}

	@Test
	public void brew_CantBrewZeroOrNegative() {
		assertThatIllegalArgumentException().isThrownBy(() -> businessImpl.brew(CoffeeTypeEnum.ESPRESSO, 0));

	}

	@Test
	public void brew_CantBrewNegative() {
		assertThatIllegalArgumentException().isThrownBy(() -> businessImpl.brew(CoffeeTypeEnum.ESPRESSO, -10));
	}

}
