package com.tw.pos.promotionRules;

import com.tw.pos.Product;
import com.tw.pos.ProductName;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SecondHalfTest {

    private SecondHalf secondHalf;
    private Product apple;

    @Before
    public void setUp() throws Exception {
        secondHalf = new SecondHalf(1);
        apple = new Product(ProductName.APPLE, 10.0);
    }

    @Test
    public void should_not_apply_this_when_buy_only_one_product() throws Exception {
        double promotionPrice = secondHalf.apply(apple.getPrice());
        assertThat(promotionPrice, is(10.0));
    }

    @Test
    public void should_apply_this_when_buy_2_products() throws Exception {
        secondHalf = new SecondHalf(2);
        double promotionPrice = secondHalf.apply(apple.getPrice());
        assertThat(promotionPrice, closeTo(7.5, 0.1));
    }

    @Test
    public void should_apply_this_when_buy_multiple_products() throws Exception {
        secondHalf = new SecondHalf(5);
        double promotionPrice = secondHalf.apply(apple.getPrice());

        assertThat(promotionPrice, closeTo(8.0, 0.1));
    }
}
