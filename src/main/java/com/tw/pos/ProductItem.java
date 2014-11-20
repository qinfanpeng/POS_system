package com.tw.pos;

/**
 * Created by fpqin on 14/11/20.
 */
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
}
