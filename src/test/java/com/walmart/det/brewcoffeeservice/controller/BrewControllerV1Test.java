package com.walmart.det.brewcoffeeservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.walmart.det.brewcoffeeservice.model.CoffeeTypeEnum;
import com.walmart.det.brewcoffeeservice.service.BrewBusinessImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(BrewControllerV1.class)
public class BrewControllerV1Test extends ControllerTests {

	@SpyBean
	private BrewBusinessImpl brewBusinessImpl;
	
	@Autowired
	private MockMvc mockMvc;

	private CoffeeTypeEnum randomCoffeType;
	private int randomQuantity; 

	@Test
	public void brewCoffee_basic() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/brew/" + randomCoffeType)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{type:" + randomCoffeType + "}]"))
				.andReturn();
	}
	
	@Test
	public void brewCoffees_ManyCoffees() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/brew/" + randomCoffeType + "/" + randomQuantity)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();

		assertThat(getTypes(result))
				.hasSize(randomQuantity)
				.allMatch(x -> x.equals(randomCoffeType.toString()));

	}

	@Test
	public void brewCoffees_ManyCoffees_Exception_ZeroQuantity() throws Exception {

		int q = 0;
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/brew/" + randomCoffeType + "/" + q)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().is4xxClientError())
				.andReturn();

	}

	@Before
	public void getRandomCoffeeType() {
		List<CoffeeTypeEnum> coffeeOptionsList = Arrays.asList(CoffeeTypeEnum.values());
		randomCoffeType = coffeeOptionsList.get(ThreadLocalRandom.current().nextInt(0, coffeeOptionsList.size()));
		randomQuantity = ThreadLocalRandom.current().nextInt(1, 10);
	}

}
