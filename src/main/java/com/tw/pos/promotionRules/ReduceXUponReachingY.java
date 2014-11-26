package com.tw.pos.promotionRules;

import com.tw.pos.Promotable;

public class ReduceXUponReachingY implements Promotion {

    private final double discount;
    private final int ceil;

    public ReduceXUponReachingY(double discount, int ceil) {
        this.discount = discount;
        this.ceil = ceil;
    }

    @Override
    public void apply(Promotable goods) {
        double promotionPrice = goods.getPromotionPrice();
        if(promotionPrice >= ceil) {
            goods.setPromotionPrice(promotionPrice - discount);
        }
    }
}
