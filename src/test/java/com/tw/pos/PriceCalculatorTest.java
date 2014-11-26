package com.tw.pos;

import com.tw.pos.promotionRules.Discount;
import com.tw.pos.promotionRules.ReduceXUponReachingY;
import com.tw.pos.promotionRules.SecondHalf;
import org.junit.Before;
import org.junit.Test;

import static com.tw.pos.ProductName.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

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

        shoppingCart.add(2, new Product(PEAR, 5));

        assertThat(priceCalculator.calculate(), is(10.0));
    }

    @Test
    public void should_calculate_total_price_with_discount() throws Exception {

        Product pearWithDiscount = new Product(PEAR, 5).with(new Discount(5));
        pearWithDiscount.promote();
        shoppingCart.add(2, pearWithDiscount);

        assertThat(priceCalculator.calculate(), is(5.0));
    }

    @Test
    public void should_calculate_total_price_with_second_half() throws Exception {

        Product pearWithDiscount = new Product(PEAR, 10).with(new SecondHalf(3));
        pearWithDiscount.promote();
        shoppingCart.add(3, pearWithDiscount);

        assertThat(priceCalculator.calculate(), is(25.0));
    }

    @Test
    public void should_calculate_total_price_with_discount_and_second_half() throws Exception {
        Product pearWithDiscountAndSecondHalf = new Product(PEAR, 10)
                .with(new SecondHalf(3))
                .with(new Discount(5));

        pearWithDiscountAndSecondHalf.promote();
        shoppingCart.add(3, pearWithDiscountAndSecondHalf);

        assertThat(priceCalculator.calculate(), closeTo(12.5, 0.1));
    }

    @Test
    public void should_calculate_total_price_with_second_half_and_discount_in_multiple_item() throws Exception {
        Product pearWithDiscountAndSecondHalf = new Product(PEAR, 10)
                .with(new Discount(7.5))
                .with(new SecondHalf(5));

        pearWithDiscountAndSecondHalf.promote();
        shoppingCart.add(5, pearWithDiscountAndSecondHalf);

        assertThat(priceCalculator.calculate(), closeTo(30, 0.1));
    }

    @Test
    public void should_calculate_total_price_with_second_half_and_discount() throws Exception {
        Product pearWithDiscountAndSecondHalf = new Product(PEAR, 10)
                .with(new Discount(7.5))
                .with(new SecondHalf(5));
        pearWithDiscountAndSecondHalf.promote();

        Product pearWithDiscount = new Product(PEAR, 10).with(new SecondHalf(3));
        pearWithDiscount.promote();

        shoppingCart.add(5, pearWithDiscountAndSecondHalf);
        shoppingCart.add(3, pearWithDiscount);

        assertThat(priceCalculator.calculate(), closeTo(55, 0.1));
    }

    @Test
    public void should_calculate_total_price_with_reduce_x_yuan_when_reaching_100() throws Exception {
        Product pear = new Product(ProductName.PEAR, 10);

        shoppingCart.add(10, pear, new ReduceXUponReachingY(5, 100));

        assertThat(priceCalculator.calculate(), closeTo(95, 0.1));
    }

    @Test
    public void should_calculate_total_price_for_shopping_cart_with_reduce_x_yuan_when_reaching_100() throws Exception {
        Product pear = new Product(ProductName.PEAR, 10);

        shoppingCart.add(11, pear, new ReduceXUponReachingY(5, 100)).with(new ReduceXUponReachingY(3, 100)).promote();

        assertThat(priceCalculator.calculate(), closeTo(102, 0.1));
    }
}
