package com.tw.pos;

import com.tw.pos.promotionRules.Promotion;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Promotable {
    private List<ProductItem> productItemList = new ArrayList<>();

    public ShoppingCart add(int amount, Product product, Promotion... promotions) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Can't add (" + amount + ")product");
        }
        productItemList.add(productItem(amount, product, promotions));

        updatePromotionPrice();
        return this;
    }

    private ProductItem productItem(int amount, Product product, Promotion[] promotions) {
        ProductItem productItem = new ProductItem(amount, product);
        for (Promotion promotion : promotions) {
            productItem.with(promotion);
        }
        productItem.promote();
        return productItem;
    }

    public int getAmountOf(ProductName productName) {
        ProductItem productItem = findProductItemBy(productName);
        int amount = 0;
        if (productItem != null) {
            amount = productItem.getAmount();
        }
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
            throw new IllegalArgumentException("There're no " + amount + " " + productName + "s to remove!");
        }

        updateProductItemList(newAmount, productItem);
    }

    private void updateProductItemList(int amount, ProductItem productItem) {
        if (amount > 0) {
            productItem.setAmount(amount);
        } else {
            productItemList.remove(productItem);
        }
        updatePromotionPrice();
    }

    private void updatePromotionPrice() {
        double totalPrice = 0.0;
        for (ProductItem productItem : productItemList) {
            totalPrice += productItem.promotionPrice;
        }
        this.promotionPrice = totalPrice;
    }

    @Override
    public double getPromotionPrice() {
        return promotionPrice;
    }

}
