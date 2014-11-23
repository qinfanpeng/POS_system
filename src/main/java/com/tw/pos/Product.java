package com.tw.pos;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private ProductName name;
    private double price;
    private double promotionPrice;

    private List<Promotion> promotionList = new ArrayList<>();

    public Product(ProductName name, double price) {
        this.name = name;
        this.price = price;
        this.promotionPrice = price;
    }

    public ProductName getName() {
        return name;
    }

    public Product with(Promotion promotion) {
        promotionList.add(promotion);
        return this;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void applyPromotion() {
        for (Promotion promotion : getPromotionList()) {
            promotion.apply(this);
        }
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
