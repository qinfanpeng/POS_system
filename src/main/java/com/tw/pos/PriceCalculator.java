package com.tw.pos;

public class PriceCalculator {
    private ShoppingCart shoppingCart;

    public PriceCalculator(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double calculate() {
        return shoppingCart.getPromotionPrice();
    }
}
