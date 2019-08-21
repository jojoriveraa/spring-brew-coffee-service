package com.walmart.det.brewcoffeeservice.data;

import com.walmart.det.brewcoffeeservice.model.ProductType;

import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

	public long queryStock(ProductType productType) {
		return 1000L;
	}
}
