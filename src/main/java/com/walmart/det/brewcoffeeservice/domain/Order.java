package com.walmart.det.brewcoffeeservice.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int id;
	private List<CoffeeItem> items = new ArrayList<>();
	
	public Order(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public List<CoffeeItem> getItems() {
		return items;
	}
	public void add(CoffeeItem item) {
		items.add(item);
	}
	public void remove(int index) {
		items.remove(index);
		
	}

}
