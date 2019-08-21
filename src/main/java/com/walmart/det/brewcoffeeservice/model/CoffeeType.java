package com.walmart.det.brewcoffeeservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CoffeeType {

	@Id
	private int id;
	private String name;
	private double price;
	private int requiredBeans;
	private int requiredMilk;

	protected CoffeeType() {
	}

	public CoffeeType(int id, String name, double price, int requiredBeans, int requiredMilk) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.requiredBeans = requiredBeans;
		this.requiredMilk = requiredMilk;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	public int getRequiredBeans() {
		return this.requiredBeans;
	}

	public int getRequiredMilk() {
		return this.requiredMilk;
	}

	@Override
	public String toString() {
		return "CoffeeType [id=" + id + ", name=" + name + ", price=" + price + ", requiredBeans=" + requiredBeans
				+ ", requiredMilk=" + requiredMilk + "]";
	}
}
