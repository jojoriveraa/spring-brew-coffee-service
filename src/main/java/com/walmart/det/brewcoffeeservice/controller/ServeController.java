package com.walmart.det.brewcoffeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.det.brewcoffeeservice.domain.Order;
import com.walmart.det.brewcoffeeservice.service.OrderManager;

@RestController
@RequestMapping("/serve")
class ServeController {

	@Autowired
	private OrderManager orderManager;

	@PutMapping("/order/{id}")
	public Order serve(@PathVariable("id") int id) {
		Order order = orderManager.getOrder(id);
		return order;
	}

}
