package com.tw.pos;

import org.junit.Before;
import org.junit.Test;

import static com.tw.pos.ProductName.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PriceCalculatorTest {

    private ShoppingCart shoppingCart;
    private PriceCalculator priceCalculator;

    @Before
    public void setUp() throws Exception {
        shoppingCart = new ShoppingCart();
        priceCalculator = new PriceCalculator(shoppingCart);
    }

    @Test
    public void should_calculate_total_price() throws Exception {

        shoppingCart.add(2, new Product(pear, 5));

        assertThat(priceCalculator.calculate(), is(10.0));
    }

    @Test
    public void should_calculate_total_price_with_discount() throws Exception {

        Product pearWithDiscount = new Product(pear, 5).with(new Discount(5));
        shoppingCart.add(2, pearWithDiscount);

        assertThat(priceCalculator.calculate(), is(5.0));
    }

    @Test
    public void should_calculate_total_price_with_second_half() throws Exception {

        Product pearWithDiscount = new Product(pear, 10).with(new SecondHalf());
        shoppingCart.add(3, pearWithDiscount);

        assertThat(priceCalculator.calculate(), is(25.0));
    }
}
