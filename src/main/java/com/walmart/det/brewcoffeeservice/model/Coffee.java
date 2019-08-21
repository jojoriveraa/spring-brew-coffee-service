package com.walmart.det.brewcoffeeservice.model;

public class Coffee {

    private final CoffeeTypeEnum type;

    public Coffee(CoffeeTypeEnum type) {
        this.type = type;
    }

    public CoffeeTypeEnum getType() {
        return type;
    }

}
