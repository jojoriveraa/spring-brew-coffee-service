package com.walmart.det.brewcoffeeservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.walmart.det.brewcoffeeservice.domain.CoffeeItem;
import com.walmart.det.brewcoffeeservice.domain.CoffeeItem.CoffeeSize;
import com.walmart.det.brewcoffeeservice.domain.CoffeeItem.CoffeeStatus;
import com.walmart.det.brewcoffeeservice.domain.CoffeeItem.CoffeeType;
import com.walmart.det.brewcoffeeservice.domain.Order;
import com.walmart.det.brewcoffeeservice.service.OrderManager;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	private final static String BASE_URI 		= "/order";
	private final static String ID_URI 			= BASE_URI + "/{id}";
	private final static String CREATE_URI 		= BASE_URI + "/create";
	private final static String ADD_ITEM_URI 	= BASE_URI + "/{id}/add/{coffeeType}/{coffeeSize}";
	private final static String REMOVE_URI 		= BASE_URI + "/{id}/remove/{itemIndex}";

	private final RequestBuilder reqToCreateOrder 			= MockMvcRequestBuilders.post(CREATE_URI);
	private final RequestBuilder reqToDeleteOrder 			= MockMvcRequestBuilders.delete(ID_URI, "0");
	private final RequestBuilder reqToGetOrder 				= MockMvcRequestBuilders.get(ID_URI, "0");
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private
	OrderManager orderManager;
	
	@Test
	public void createOrderTest_basic() throws Exception {
		
		when(orderManager.createNewOrder())
				.thenReturn(new Order(0));
		
		this.mockMvc.perform(reqToCreateOrder)
				.andExpect(status().isCreated())
				.andExpect(content().json("{id:0,items:[]}"));
	}
	
	@Test
	public void createOrderTest_ManyOrders() throws Exception {
		
		when(orderManager.createNewOrder())
				.thenReturn(new Order(0))
				.thenReturn(new Order(1));
		
		this.mockMvc.perform(reqToCreateOrder)
				.andExpect(status().isCreated())
				.andExpect(content().json("{id:0,items:[]}"));
		
		this.mockMvc.perform(reqToCreateOrder)
				.andExpect(status().isCreated())
				.andExpect(content().json("{id:1,items:[]}"));
	}

	@Test
	public void deleteOrderTest_basic() throws Exception {
		
		when(orderManager.deleteOrder(0))
				.thenReturn(true);
		
		this.mockMvc.perform(reqToDeleteOrder)
				.andExpect(status().isNoContent());

	}
	
	@Test
	public void deleteOrderTest_inexistentOrder() throws Exception {
		this.mockMvc.perform(reqToDeleteOrder)
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getOrderTest_basic() throws Exception {
		
		when(orderManager.getOrder(0))
				.thenReturn(new Order(0));
		
		this.mockMvc.perform(reqToGetOrder)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:0,items:[]}"));
	}
	
	@Test
	public void getOrderTest_withOneProduct() throws Exception {
		
		Order order = new Order(0);
		order.add(new CoffeeItem(CoffeeType.LATTE, CoffeeSize.MEDIUM));
		
		when(orderManager.getOrder(0))
				.thenReturn(order);
		
		this.mockMvc.perform(reqToGetOrder)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:0,items:[{name: LATTE, size: MEDIUM, price: 0.0, status: WAITING}]}"));
	}
	
	@Test
	public void getOrderTest_withManyProducts() throws Exception {
		
		Order order = new Order(0);
		order.add(new CoffeeItem(CoffeeType.LATTE, CoffeeSize.MEDIUM));
		order.add(new CoffeeItem(CoffeeType.ESPRESSO, CoffeeSize.LARGE));
		order.getItems().get(1).setStatus(CoffeeStatus.DONE);
		
		when(orderManager.getOrder(0))
				.thenReturn(order);
		
		this.mockMvc.perform(reqToGetOrder)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:0,items:["
						+ "{name: LATTE, size: MEDIUM, price: 0.0, status: WAITING},"
						+ "{name: ESPRESSO, size: LARGE, price: 0.0, status: DONE}"
						+ "]}"));
	}
	
	@Test
	@DirtiesContext
	public void addCoffeeItemToOrder_basic() throws Exception {
		
		when(orderManager.getOrder(0))
				.thenReturn(new Order(0));
		
		RequestBuilder reqToAddProductToOrder = MockMvcRequestBuilders
				.put(ADD_ITEM_URI, "0", CoffeeItem.CoffeeType.LATTE, CoffeeItem.CoffeeSize.SMALL);
		
		this.mockMvc.perform(reqToAddProductToOrder)
				.andExpect(status().isAccepted())
				.andExpect(content().json("{id:0,items:["
						+ "{name: LATTE, size: SMALL, price: 0.0, status: WAITING}"
						+ "]}"));
		
	}
	
	@Test
	@DirtiesContext
	public void addCoffeeItemToOrder_ManyItems() throws Exception {
		
		when(orderManager.getOrder(0))
				.thenReturn(new Order(0));
		
		RequestBuilder reqToAddFirstProductToOrder = MockMvcRequestBuilders
				.put(ADD_ITEM_URI, "0", CoffeeItem.CoffeeType.LATTE, CoffeeItem.CoffeeSize.SMALL);
		
		RequestBuilder reqToAddSeccondProductToOrder = MockMvcRequestBuilders
				.put(ADD_ITEM_URI, "0", CoffeeItem.CoffeeType.ESPRESSO, CoffeeItem.CoffeeSize.LARGE);
		
		this.mockMvc.perform(reqToAddFirstProductToOrder)
				.andExpect(status().isAccepted());
		
		this.mockMvc.perform(reqToAddSeccondProductToOrder)
				.andExpect(status().isAccepted())
				.andExpect(content().json("{id:0,items:["
						+ "{name: LATTE, size: SMALL, price: 0.0},"
						+ "{name: ESPRESSO, size: LARGE, price: 0.0}]}"));
	}

	@Test
	public void removeProductFromOrder_basic() throws Exception {
		
		Order order = new Order(0);
		order.add(new CoffeeItem(CoffeeType.LATTE, CoffeeSize.MEDIUM));
		
		when(orderManager.getOrder(0))
				.thenReturn(order);
		
		RequestBuilder reqToRemoveProductFromOrder = MockMvcRequestBuilders
				.delete(REMOVE_URI, "0", "0");
		
		this.mockMvc.perform(reqToRemoveProductFromOrder)
				.andExpect(status().isAccepted())
				.andExpect(content().json("{id:0,items:[]}"));

	}
	
	@Test
	public void removeProductFromOrder_deleteAll() throws Exception {
		
		Order order = new Order(0);
		order.add(new CoffeeItem(CoffeeType.LATTE, CoffeeSize.MEDIUM));
		order.add(new CoffeeItem(CoffeeType.ESPRESSO, CoffeeSize.LARGE));
		order.add(new CoffeeItem(CoffeeType.MACCHIATO, CoffeeSize.SMALL));
		order.getItems().get(1).setStatus(CoffeeStatus.DONE);
		
		when(orderManager.getOrder(0))
				.thenReturn(order);
		
		RequestBuilder reqToRemoveProductFromOrder = MockMvcRequestBuilders
				.delete(REMOVE_URI, "0", "0");
		
		
		this.mockMvc.perform(reqToRemoveProductFromOrder);
		this.mockMvc.perform(reqToRemoveProductFromOrder);
		this.mockMvc.perform(reqToRemoveProductFromOrder)
				.andExpect(status().isAccepted())
				.andExpect(content().json("{id:0,items:[]}"));

	}
	

}
