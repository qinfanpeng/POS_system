package com.tw.pos;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ProductItem> productItemList = new ArrayList<ProductItem>();

    public void add(int amount, Product product) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Can't add (" + amount + ")product");
        }
        ProductItem productItem = new ProductItem(amount, product);
        productItemList.add(productItem);
    }

    public int getAmountOf(ProductName productName) {
        ProductItem productItem = findProductItemBy(productName);
        int amount = 0;
        if (productItem != null) { amount = productItem.getAmount(); }
        return amount;
    }

    private ProductItem findProductItemBy(ProductName productName) {
        ProductItem result = null;
        for (ProductItem productItem : productItemList) {
            if (productItem.getProductName().equals(productName)) {
                result = productItem;
            }
        }
        return result;
    }

    public void remove(int amount, ProductName productName) {
        ProductItem productItem = findProductItemBy(productName);
        if (productItem == null) {
            throw new IllegalArgumentException("Can't find any " + productName + " to remove!");
        }

        int newAmount = productItem.getAmount() - amount;

        if (newAmount < 0) {
            throw new IllegalArgumentException("There're no " + amount + " " + productName +"s to remove!");
        }

        updateProductItemList(newAmount, productItem);
    }

    private void updateProductItemList(int amount, ProductItem productItem) {
        if (amount > 0) {
            productItem.setAmount(amount);
        } else {
            productItemList.remove(productItem);
        }
    }

    public List<ProductItem> getProductItemList() {
        return productItemList;
    }
}
