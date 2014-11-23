package com.tw.pos;

public class ProductItem extends Promotable {
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
        return product.getPromotionPrice() * amount;
    }

    @Override
    public double getPromotionPrice() {
        return getSubtotal();
    }

    @Override
    public void setPromotionPrice(double promotionPrice) {
    }
}