package com.walmart.det.brewcoffeeservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.walmart.det.brewcoffeeservice.domain.CoffeeItem;
import com.walmart.det.brewcoffeeservice.domain.CoffeeItem.CoffeeSize;
import com.walmart.det.brewcoffeeservice.domain.CoffeeItem.CoffeeType;
import com.walmart.det.brewcoffeeservice.domain.Order;
import com.walmart.det.brewcoffeeservice.service.BrewServiceImpl;
import com.walmart.det.brewcoffeeservice.service.OrderManager;

@RunWith(SpringRunner.class)
@WebMvcTest(BrewController.class)
public class BrewControllerTest extends ControllerTests {
	

	private final static String BASE_URI 		= "/brew/order";
	private final static String BREW_ORDER_URI 	= BASE_URI + "/{id}";

	private final RequestBuilder reqToBrewOrder = MockMvcRequestBuilders.put(BREW_ORDER_URI, "0");

	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private BrewServiceImpl brewService;
	
	@MockBean
	private OrderManager orderManager;

	@Test
	public void brewOrder_basic() throws Exception {

		Order order = new Order(0);
		order.add(new CoffeeItem(CoffeeType.LATTE, CoffeeSize.MEDIUM));
		when(orderManager.getOrder(0)).thenReturn(order);
		
		mockMvc.perform(reqToBrewOrder)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:0,items:[{name: LATTE, size: MEDIUM, price: 0.0, status: DONE}]}"));
	}
	
	@Test
	public void brewOrder_manyItems() throws Exception {

		Order order = new Order(0);
		order.add(new CoffeeItem(CoffeeType.LATTE, CoffeeSize.MEDIUM));
		order.add(new CoffeeItem(CoffeeType.MACCHIATO, CoffeeSize.SMALL));
		when(orderManager.getOrder(0)).thenReturn(order);
		
		mockMvc.perform(reqToBrewOrder)
				.andExpect(status().isOk())
				.andExpect(content().json("{items:["
						+ "{name: LATTE, size: MEDIUM, price: 0.0, status: DONE},"
						+ "{name: MACCHIATO, size: SMALL, price: 0.0, status: DONE}"
						+ "]}"));
	}
	
	@Test
	public void brewOrder_emptyOrder() throws Exception {

		Order order = new Order(0);
		when(orderManager.getOrder(0)).thenReturn(order);
		
		mockMvc.perform(reqToBrewOrder)
				.andExpect(status().isOk())
				.andExpect(content().json("{items:[]}"));
	}

}
