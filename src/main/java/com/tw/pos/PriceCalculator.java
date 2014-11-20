package com.tw.pos;

/**
 * Created by fpqin on 14/11/20.
 */
public class PriceCalculator {
    private ShoppingCart shoppingCart;

    public PriceCalculator(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double calculate() {
        double totalPrice = 0.0;
        for (ProductItem productItem : shoppingCart.getProductItemList()) {
            totalPrice += productItem.getSubtotal();
        }
        return totalPrice;
    }
}
