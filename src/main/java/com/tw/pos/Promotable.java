package com.tw.pos;

import com.tw.pos.promotionRules.Promotion;

import java.util.ArrayList;
import java.util.List;

public abstract class Promotable<T extends Promotable> {
    protected double promotionPrice;
    private List<Promotion<Double>> promotionRules = new ArrayList<>();

    protected T with(Promotion<Double> promotion) {
        promotionRules.add(promotion);
        return (T)this;
    }

    public void promote() {
        for (Promotion<Double> promotion : promotionRules) {
            promotionPrice = promotion.apply(promotionPrice);
        }
    }

    public abstract double getPromotionPrice();
}
