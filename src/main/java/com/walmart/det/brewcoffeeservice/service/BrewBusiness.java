package com.walmart.det.brewcoffeeservice.service;

import java.util.List;

import com.walmart.det.brewcoffeeservice.model.Coffee;
import com.walmart.det.brewcoffeeservice.model.CoffeeTypeEnum;

public interface BrewBusiness {

	List<Coffee> brew(CoffeeTypeEnum type);

	List<Coffee> brew(CoffeeTypeEnum type, int quantity);

}