package com.tw.pos.promotionRules;

import com.tw.pos.Promotable;

public class ReduceXUponReaching100 implements Promotion {

    private final double x;

    public ReduceXUponReaching100(double x) {
        this.x = x;
    }

    @Override
    public void apply(Promotable goods) {
        double promotionPrice = goods.getPromotionPrice();
        if(promotionPrice >= 100) {
            goods.setPromotionPrice(promotionPrice - x);
        }
    }
}
