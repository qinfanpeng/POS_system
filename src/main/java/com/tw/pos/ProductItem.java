package com.tw.pos;

import com.tw.pos.promotionRules.Promotion;

public class ProductItem extends Promotable {
    private int amount;
    private Product product;
    private double promotionPrice;

    public ProductItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
        this.promotionPrice = product.getPromotionPrice() * amount;
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
        return promotionPrice;
    }

    @Override
    public ProductItem with(Promotion promotion) {
        super.with(promotion);
        return this;
    }

    @Override
    public double getPromotionPrice() {
        return promotionPrice;
    }

    @Override
    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}