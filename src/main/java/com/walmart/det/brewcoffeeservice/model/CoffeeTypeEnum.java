package com.walmart.det.brewcoffeeservice.model;

public enum CoffeeTypeEnum {
    ESPRESSO(7, 0), LATTE(3, 200), AMERICANO(2, 0), CAPUCCINO(7, 200);

    private final int requiredBeans;
    private final int requiredMilk;

    CoffeeTypeEnum(int requiredBeans, int requiredMilk) {
        this.requiredBeans = requiredBeans;
        this.requiredMilk = requiredMilk;
    }

    public int requiredBeans() {
        return requiredBeans;
    }

    public int requiredMilk() {
        return requiredMilk;
    }
}
