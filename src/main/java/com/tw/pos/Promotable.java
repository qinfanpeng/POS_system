package com.tw.pos;

import com.tw.pos.promotionRules.Promotion;

import java.util.ArrayList;
import java.util.List;

public abstract class Promotable {
    private List<Promotion> promotionRules = new ArrayList<>();

    protected Promotable with(Promotion promotion) {
        promotionRules.add(promotion);
        return this;
    }

    public void promote() {
        for (Promotion promotion : promotionRules) {
            promotion.apply(this);
        }
    }

    public abstract double getPromotionPrice();

    public abstract void setPromotionPrice(double promotionPrice);
}
