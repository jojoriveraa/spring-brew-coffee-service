package com.walmart.det.brewcoffeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.det.brewcoffeeservice.exceptions.CoffeTypeNotImplementedException;
import com.walmart.det.brewcoffeeservice.model.Coffee;
import com.walmart.det.brewcoffeeservice.model.CoffeeTypeEnum;
import com.walmart.det.brewcoffeeservice.service.BrewBusiness;

@RestController
@RequestMapping("/api/v1/brew")
public class BrewControllerV1 {

	@Autowired
	private BrewBusiness brewBusiness;

	@GetMapping("/{type}")
	public List<Coffee> brewCoffee(@PathVariable("type") String type) {
		try {
			CoffeeTypeEnum coffeType = CoffeeTypeEnum.valueOf(type.toUpperCase());
			return brewBusiness.brew(coffeType);
		} catch (IllegalArgumentException e) {
			throw new CoffeTypeNotImplementedException(type, e);
		}
	}

	@GetMapping("/{type}/{quantity}")
	public List<Coffee> brewManyCoffees(
			@PathVariable("type") String type,
			@PathVariable("quantity") int quantity) {
		try {
			CoffeeTypeEnum coffeType = CoffeeTypeEnum.valueOf(type.toUpperCase());
			return brewBusiness.brew(coffeType, quantity);
		} catch (CoffeTypeNotImplementedException e) {
			throw new CoffeTypeNotImplementedException(type, e);
		} 
	}
}