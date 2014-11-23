package com.tw.pos;

import com.tw.pos.promotionRules.Promotion;

public class Product extends Promotable {
    private ProductName name;
    private double price;
    private double promotionPrice;

    public Product(ProductName name, double price) {
        this.name = name;
        this.price = price;
        this.promotionPrice = price;
    }

    public ProductName getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Product with(Promotion promotion) {
        super.with(promotion);
        return this;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
