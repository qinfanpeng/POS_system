package com.tw.pos;

/**
 * Created by fpqin on 14/11/20.
 */
public class Discount implements Promotion{
    private double rate;

    public Discount(double rate) {
        if (rate < 0.0 || rate > 10.0 || moreThanTwoDigit(rate)) {
            throw new IllegalArgumentException("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        }
        this.rate = rate;
    }

    private boolean moreThanTwoDigit(double rate) {
        return Double.toString(rate).length() > 3;
    }

    @Override
    public void apply(int amount, Product product) {
        product.setPromotionPrice(product.getPromotionPrice() * (rate / 10));
    }
}
