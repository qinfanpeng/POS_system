package com.tw.pos.promotionRules;

import com.tw.pos.Product;
import com.tw.pos.ProductName;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class ReduceXUponReaching100Test {

    @Test
    public void should_reduce_5_yuan_when_product_price_reach_100() throws Exception {
        ReduceXUponReaching100 rule = new ReduceXUponReaching100(5);
        Product apple = new Product(ProductName.apple, 100);

        rule.apply(apple);

        assertThat(apple.getPromotionPrice(), closeTo(95.0, 0.1));
    }
}