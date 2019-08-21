package com.walmart.det.brewcoffeeservice.data;

import com.walmart.det.brewcoffeeservice.model.ProductType;

public interface StockService {
	
	long queryStock(ProductType productType);

}