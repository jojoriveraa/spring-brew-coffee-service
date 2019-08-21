package com.walmart.det.brewcoffeeservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.det.brewcoffeeservice.domain.CoffeeItem;
import com.walmart.det.brewcoffeeservice.domain.Order;
import com.walmart.det.brewcoffeeservice.service.OrderManager;

@RestController
@RequestMapping("/order")
class OrderController {

	@Autowired
	private
	OrderManager manager;

	@PostMapping("/create")
	public Order create(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_CREATED);
		return manager.createNewOrder();
	}

	@GetMapping("/{id}")
	public Order get(@PathVariable("id") int id, HttpServletResponse response) {
		Order order = manager.getOrder(id);
		
		if (order == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		return order;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id, HttpServletResponse response) {
		boolean found = manager.deleteOrder(id);
		if (found) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	@PutMapping("/{id}/add/{coffeeType}/{coffeeSize}")
	public Order add(@PathVariable("id") int id, @PathVariable("coffeeType") String coffeeType,
			@PathVariable("coffeeSize") String coffeeSize, HttpServletResponse response) {
		CoffeeItem item = new CoffeeItem(coffeeType, coffeeSize);
		Order order = manager.getOrder(id);
		order.add(item);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return order;
	}

	@DeleteMapping("/{id}/remove/{itemIndex}")
	public Order remove(@PathVariable("id") int id, @PathVariable("itemIndex") int index,
			HttpServletResponse response) {
		Order order = manager.getOrder(id);
		order.remove(index);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return order;
	}

}
