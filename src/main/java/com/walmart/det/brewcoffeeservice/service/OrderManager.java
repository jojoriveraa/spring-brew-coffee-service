package com.walmart.det.brewcoffeeservice.service;

import com.walmart.det.brewcoffeeservice.domain.Order;

public interface OrderManager {

	Order createNewOrder();

	Order getOrder(int id);

	boolean deleteOrder(int id);


}
