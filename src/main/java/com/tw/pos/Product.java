package com.tw.pos;

public class Product extends Promotable<Product> {
    private ProductName name;
    private double price;

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

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
