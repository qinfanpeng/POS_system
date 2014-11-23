package com.tw.pos;

public class Discount implements Promotion{
    private double rate;

    public Discount(double rate) {
        if (rate < 0.0 || rate > 10.0 || moreThanOneDecimal(rate)) {
            throw new IllegalArgumentException("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        }
        this.rate = rate;
    }

    private boolean moreThanOneDecimal(double rate) {
        String number = Double.toString(rate);
        return number.substring(number.indexOf('.')+1).length() > 1;
    }

    @Override
    public void apply(int amount, Product product) {
        product.setPromotionPrice(product.getPromotionPrice() * (rate / 10));
    }
}
