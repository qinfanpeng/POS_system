package com.tw.pos;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.tw.pos.ProductName.apple;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DiscountTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_ensure_in_between_0_and_10() throws Exception {
        expectedException.expectMessage("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        new Discount(11.1);
    }

    @Test
    public void should_be_between_0_to_10() throws Exception {
        expectedException.expectMessage("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        new Discount(5.55);
    }

    @Test
    public void should_be_discount_product() throws Exception {
        Product fiveDiscountApple = new Product(apple, 8).with(new Discount(5));

        assertThat(fiveDiscountApple.getPromotionPrice(), is(4d));
    }


}
