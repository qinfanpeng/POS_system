package com.tw.pos.promotionRules;

import com.tw.pos.Product;
import com.tw.pos.ProductName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DiscountTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_ensure_less_than_10() throws Exception {
        expectedException.expectMessage("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        new Discount(11.1);
    }

    @Test
    public void should_be_ensure_greater_than_0() throws Exception {
        expectedException.expectMessage("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        new Discount(-0.1);
    }

    @Test
    public void should_not_only_have_one_decimal() throws Exception {
        expectedException.expectMessage("Discount rate should be between 0.0 to 10.0, like 5 or 7.5!");
        new Discount(5.55);
    }

    @Test
    public void should_be_discount_product() throws Exception {
        Discount fiveDiscount = new Discount(5);
        Product apple = new Product(ProductName.apple, 8);

        fiveDiscount.apply(apple);

        assertThat(apple.getPromotionPrice(), is(4d));
    }
}
