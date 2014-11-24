package com.tw.pos;

import com.tw.pos.promotionRules.Promotion;

import java.util.ArrayList;
import java.util.List;

public abstract class Promotable<T extends Promotable> {
    protected double promotionPrice;
    private List<Promotion> promotionRules = new ArrayList<>();

    protected T with(Promotion promotion) {
        promotionRules.add(promotion);
        return (T)this;
    }

    public void promote() {
        for (Promotion promotion : promotionRules) {
            promotion.apply(this);
        }
    }

    public abstract double getPromotionPrice();

    public abstract void setPromotionPrice(double promotionPrice);
}
