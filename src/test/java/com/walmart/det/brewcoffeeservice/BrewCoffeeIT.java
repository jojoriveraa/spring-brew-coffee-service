package com.walmart.det.brewcoffeeservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.walmart.det.brewcoffeeservice.controller.BrewControllerV1;
import com.walmart.det.brewcoffeeservice.data.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BrewCoffeeIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockService mockStockService;

	@Autowired
	private BrewControllerV1 brewController;

	@Test
	public void contextLoads() {
		assertThat(brewController).isNotNull();
	}

	public void brew_Basic() throws Exception {

		this.mockMvc.perform(get("/api/brew/espresso")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$..type", everyItem(is("ESPRESSO"))));
	}

	public void brew_JsonResponseIsOK() throws Exception {

		this.mockMvc.perform(get("/api/brew/latte")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$..type", everyItem(is("LATTE"))));
	}

	public void brew_ManyBeverages() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/api/brew/espresso/7").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(7)))
				.andExpect(jsonPath("$..type", everyItem(is("ESPRESSO"))));

	}

	public void brew_ExceptionWhenBrewZeroOrNegativeBeverages() throws Exception {
		this.mockMvc.perform(get("/api/brew/espresso/0")).andExpect(status().is4xxClientError());
	}

	public void brew_ExceptionWhenBrewANotImplementedCoffeeType() throws Exception {
		this.mockMvc.perform(get("/api/brew/capuccino")).andExpect(status().is4xxClientError());
	}

}
