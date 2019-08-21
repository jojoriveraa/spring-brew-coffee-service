package com.walmart.det.brewcoffeeservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.walmart.det.brewcoffeeservice.domain.Order;

@Service
public class OrderManagerImpl implements OrderManager {

	private int count;
	private Map<Integer, Order> orders;

	public OrderManagerImpl() {
		count = 0;
		orders = new HashMap<>();
	}

	@Override
	public Order createNewOrder() {
		Order order = new Order(count++);
		orders.put(order.getId(), order);
		return order;
	}

	@Override
	public Order getOrder(int id) {
		return orders.get(id);
	}

	@Override
	public boolean deleteOrder(int id) {
		if (orders.get(id) != null) {
			orders.remove(id);
			return true;
		}
		return false;
	}

}
