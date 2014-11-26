package com.tw.pos.promotionRules;

public class ReduceXUponReachingY implements Promotion<Double> {

    private final double discount;
    private final int ceil;

    public ReduceXUponReachingY(double discount, int ceil) {
        this.discount = discount;
        this.ceil = ceil;
    }

    @Override
    public Double apply(Double value) {
        return value >= ceil ? Double.valueOf(value - discount) : value;
    }
}
