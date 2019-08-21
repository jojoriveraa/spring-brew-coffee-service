package com.walmart.det.brewcoffeeservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.walmart.det.brewcoffeeservice.domain.Order;

public class OrderManagerTest {

	private final OrderManager manager = new OrderManagerImpl();
	private final OrderManager spyManager = spy(manager);

	private Order dummyOrder;

	@Before
	public void init() {
		dummyOrder = spyManager.createNewOrder();
	}

	@Test
	public void createOrder_Basic() {
		verify(spyManager).createNewOrder();
		assertThat(dummyOrder).isNotNull();
	}

	@Test
	public void getOrder_Basic() {
		Order order = spyManager.getOrder(0);
		verify(spyManager).getOrder(0);
		assertThat(order).isEqualTo(dummyOrder);
	}

	@Test
	public void deleteOrder_Basic() {
		boolean found = spyManager.deleteOrder(0);
		verify(spyManager).deleteOrder(0);
		assertThat(found).isTrue();
	}

	@Test
	public void deleteOrder_NotExistentOrder() {
		boolean found = spyManager.deleteOrder(1);
		verify(spyManager).deleteOrder(1);
		assertThat(found).isFalse();
	}

}
