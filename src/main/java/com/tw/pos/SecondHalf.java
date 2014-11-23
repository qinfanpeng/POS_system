package com.tw.pos;

public class SecondHalf implements Promotion {
    private int amount;

    public SecondHalf(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Product product) {
        double promotionTotalPrice = 0.0;

        for (int i = 1; i<= amount; i++) {
            if (i % 2 == 0 ) {
                promotionTotalPrice += product.getPromotionPrice() / 2;
            } else {
                promotionTotalPrice += product.getPromotionPrice();
            }
        }

        double promotionPrice = promotionTotalPrice / amount;
        product.setPromotionPrice(promotionPrice);
    }
}
