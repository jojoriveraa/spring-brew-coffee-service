package com.walmart.det.brewcoffeeservice.domain;

public class CoffeeItem {

	public enum CoffeeSize {
		SMALL, MEDIUM, LARGE
	}

	public enum CoffeeType {
		ESPRESSO, LATTE, MACCHIATO
	}

	public enum CoffeeStatus {
		WAITING, DONE, SERVED
	}

	private CoffeeType name;
	private CoffeeSize size;
	private CoffeeStatus status;

	public CoffeeItem(CoffeeType name, CoffeeSize size) {
		this.name = name;
		this.size = size;
		this.status = CoffeeStatus.WAITING;
	}

	public CoffeeItem(String name, String size) {
		this.name = CoffeeType.valueOf(name);
		this.size = CoffeeSize.valueOf(size);
		this.status = CoffeeStatus.WAITING;
	}

	public CoffeeType getName() {
		return name;
	}

	public void setName(CoffeeType name) {
		this.name = name;
	}

	public CoffeeSize getSize() {
		return size;
	}

	public void setSize(CoffeeSize size) {
		this.size = size;
	}

	public CoffeeStatus getStatus() {
		return status;
	}

	public void setStatus(CoffeeStatus status) {
		this.status = status;
	}

	public Double getPrice() {
		// TODO: implement get price
		return 0.0;
	}

}
