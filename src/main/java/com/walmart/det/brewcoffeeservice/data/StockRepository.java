package com.walmart.det.brewcoffeeservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walmart.det.brewcoffeeservice.model.CoffeeType;


public interface StockRepository extends JpaRepository<CoffeeType, Integer>{

}
