package com.tw.pos;

/**
 * Created by fpqin on 14/11/20.
 */
public class Discount implements Promotion{
    private float rate;

    public Discount(float rate) {
        this.rate = rate;
    }

    @Override
    public void doPromotion(Product product) {
        double virtualPrice = product.getPrice() * (rate / 10);
        product.setPrice(virtualPrice);
    }
}
