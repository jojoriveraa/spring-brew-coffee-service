package com.walmart.det.brewcoffeeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walmart.det.brewcoffeeservice.domain.CoffeeItem;
import com.walmart.det.brewcoffeeservice.domain.CoffeeItem.CoffeeStatus;

@Service
public class BrewServiceImpl implements BrewService {

	@Override
	public void brew(List<CoffeeItem> items) {
		items.forEach(item -> item.setStatus(CoffeeStatus.DONE));
	}
}
