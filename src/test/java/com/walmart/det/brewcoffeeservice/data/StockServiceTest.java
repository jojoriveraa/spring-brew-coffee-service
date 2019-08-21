package com.walmart.det.brewcoffeeservice.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.walmart.det.brewcoffeeservice.model.ProductType;

public class StockServiceTest {

	private StockService stockService;
	
	@Before
	public void setup() {
		stockService = new StockServiceImpl();
	}

	@Test
	public void alwaysReturn1000() {
		assertThat(stockService.queryStock(ProductType.BEANS)).isEqualTo(1000L);
	}

}
