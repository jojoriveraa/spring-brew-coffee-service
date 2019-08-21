package com.walmart.det.brewcoffeeservice.model;

public enum ProductType {
    BEANS("00000"), MILK("11111");

    private final String sku;

    ProductType(String sku) {
        this.sku = sku;
    }
    public String getSku() {
        return sku;
    }
}