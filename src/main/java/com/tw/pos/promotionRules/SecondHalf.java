package com.tw.pos.promotionRules;

import com.tw.pos.Promotable;

public class SecondHalf implements Promotion {
    private int amount;

    public SecondHalf(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Promotable goods) {
        double promotionTotalPrice = 0.0;

        for (int i = 1; i<= amount; i++) {
            if (i % 2 == 0 ) {
                promotionTotalPrice += goods.getPromotionPrice() / 2;
            } else {
                promotionTotalPrice += goods.getPromotionPrice();
            }
        }

        double promotionPrice = promotionTotalPrice / amount;
        goods.setPromotionPrice(promotionPrice);
    }
}
