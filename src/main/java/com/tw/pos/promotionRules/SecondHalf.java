package com.tw.pos.promotionRules;

public class SecondHalf implements Promotion<Double> {
    private int amount;

    public SecondHalf(int amount) {
        this.amount = amount;
    }

    @Override
    public Double apply(Double value) {
        int numOfEven = amount / 2;
        double promotionTotalPrice = amount * value - numOfEven * value / 2;
        return promotionTotalPrice / amount;
    }
}
