package com.tw.pos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fpqin on 14/11/20.
 */
public class Product {

    private ProductName name;
    private double price;
    private List<Promotion> promotionList = new ArrayList<>();

    public Product(ProductName name, double price) {
        this.name = name;
        this.price = price;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }


    public void doPromotion() {
        for (Promotion promotion : getPromotionList()) {
            promotion.doPromotion(this);
        }
    }
}
