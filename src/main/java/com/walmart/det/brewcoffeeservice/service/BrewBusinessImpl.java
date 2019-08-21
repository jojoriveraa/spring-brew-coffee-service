package com.walmart.det.brewcoffeeservice.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.walmart.det.brewcoffeeservice.exceptions.IllegalQuantityException;
import com.walmart.det.brewcoffeeservice.model.Coffee;
import com.walmart.det.brewcoffeeservice.model.CoffeeTypeEnum;

@Service
public class BrewBusinessImpl implements BrewBusiness {

	@Override
	public List<Coffee> brew(CoffeeTypeEnum type) { return brew(type, 1); }

	@Override
	public List<Coffee> brew(CoffeeTypeEnum type, int quantity) {

		if (quantity < 1) { throw new IllegalQuantityException(quantity);}

		return IntStream.range(0, quantity)
				.mapToObj(x -> new Coffee(type))
				.collect(Collectors.toList());
	}
}