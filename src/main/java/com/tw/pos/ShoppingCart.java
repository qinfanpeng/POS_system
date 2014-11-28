package com.tw.pos;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.tw.pos.promotionRules.Promotion;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.tryFind;

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

    public int amountOf(ProductName productName) {
        return find(by(productName)).or(new ProductItem(0, new Product(null, 0.0))).getAmount();
    }

    private Predicate<ProductItem> by(final ProductName name) {
        return new Predicate<ProductItem>() {
            @Override
            public boolean apply(ProductItem item) {
                return item.isFor(name);
            }
        };
    }

    private Optional<ProductItem> find(Predicate<ProductItem> match) {
        return tryFind(productItemList, match);
    }

    public void remove(int amount, ProductName productName) {
        Optional<ProductItem> productItem = find(by(productName));
        if (!productItem.isPresent()) {
            throw new IllegalArgumentException("Can't find any " + productName + " to remove!");
        }

        int restOfAmount = productItem.get().getAmount() - amount;

        if (restOfAmount < 0) {
            throw new IllegalArgumentException("There're no " + amount + " " + productName + "s to remove!");
        }

        updateProductItemList(restOfAmount, productItem.get());
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

    public void add(Product product) {
        add(1, product);
    }
}
