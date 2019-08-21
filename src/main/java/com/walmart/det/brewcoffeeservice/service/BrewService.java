package com.walmart.det.brewcoffeeservice.service;

import java.util.List;

import com.walmart.det.brewcoffeeservice.domain.CoffeeItem;

public interface BrewService {

	void brew(List<CoffeeItem> items);

}
