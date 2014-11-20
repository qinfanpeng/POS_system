package com.tw.pos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fpqin on 14/11/20.
 */
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

    public double getPrice() {
        return price;
    }

    public Product with(Promotion promotion) {
        promotionList.add(promotion);
        return this;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }


    private void doPromotion() {
        for (Promotion promotion : getPromotionList()) {
            promotion.doPromotion(this);
        }
    }

    public double getPromotionPrice() {
        this.doPromotion();
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
