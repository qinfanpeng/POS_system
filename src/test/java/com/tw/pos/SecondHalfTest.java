package com.tw.pos;

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
        secondHalf = new SecondHalf();
        apple = new Product(ProductName.apple, 10.0);
    }

    @Test
    public void should_not_apply_this_when_buy_only_one_product() throws Exception {
        secondHalf.apply(1, apple);
        assertThat(apple.getPromotionPrice(), is(10.0));
    }

    @Test
    public void should_apply_this_when_buy_2_products() throws Exception {

        secondHalf.apply(2, apple);
        assertThat(apple.getPromotionPrice(), closeTo(7.5, 0.1));
    }

    @Test
    public void should_apply_this_when_buy_3_products() throws Exception {

        secondHalf.apply(3, apple);
        assertThat(apple.getPromotionPrice(), closeTo(8.3, 0.1));
    }

    @Test
    public void should_apply_this_when_buy_4_products() throws Exception {

        secondHalf.apply(4, apple);
        assertThat(apple.getPromotionPrice(), closeTo(7.5, 0.1));
    }

    @Test
    public void should_apply_this_when_buy_5_products() throws Exception {

        secondHalf.apply(5, apple);
        assertThat(apple.getPromotionPrice(), closeTo(8.0, 0.1));
    }
}
