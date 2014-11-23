package com.tw.pos;

public class ProductItem {
    private int amount;
    private Product product;

    public ProductItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductName getProductName() {
        return product.getName();
    }

    public double getSubtotal() {
        product.applyPromotion();
        return product.getPromotionPrice() * amount;
    }
}