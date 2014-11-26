package com.tw.pos;

public class ProductItem extends Promotable<ProductItem> {
    private int amount;
    private Product product;

    public ProductItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
        this.promotionPrice = product.promotionPrice * amount;
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

    @Override
    public double getPromotionPrice() {
        return promotionPrice;
    }
}