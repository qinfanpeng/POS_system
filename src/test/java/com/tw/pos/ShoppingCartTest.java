package com.tw.pos;

import com.tw.pos.promotionRules.Discount;
import com.tw.pos.promotionRules.ReduceXUponReachingY;
import com.tw.pos.promotionRules.SecondHalf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.tw.pos.ProductName.PEAR;
import static com.tw.pos.ProductName.APPLE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class ShoppingCartTest{

    private static ShoppingCart shoppingCart;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void should_add_product() throws Exception {
        shoppingCart.add(1, new Product(APPLE, 0));

        assertThat(shoppingCart.amountOf(APPLE), is(1));
    }

    @Test
    public void should_add_products() throws Exception {
        shoppingCart.add(1, new Product(APPLE, 0));
        shoppingCart.add(2, new Product(PEAR, 0));

        assertThat(shoppingCart.amountOf(APPLE), is(1));
        assertThat(shoppingCart.amountOf(PEAR), is(2));
    }

    @Test
    public void should_add_one_when_not_specify_amount_of_product() throws Exception {
        shoppingCart.add(new Product(APPLE, 0));

        assertThat(shoppingCart.amountOf(APPLE), is(1));
    }

    @Test
    public void should_throw_exception_when_add_zero_product() throws Exception {
        expectedException.expectMessage("Can't add (0)product");
        shoppingCart.add(0, new Product(APPLE, 0));
    }

    @Test
    public void should_be_able_to_remove_product() throws Exception {
        shoppingCart.add(1, new Product(APPLE, 5));
        shoppingCart.remove(1, APPLE);

        assertThat(shoppingCart.amountOf(APPLE), is(0));
    }

    @Test
    public void should_throw_exception_when_try_to_remove_a_non_exist_product() throws Exception {
        expectedException.expectMessage("Can't find any apple to remove!");

        shoppingCart.remove(1, APPLE);
    }

    @Test
    public void should_throw_exception_when_try_to_remove_excessive_products() throws Exception {
        expectedException.expectMessage("There're no 2 apples to remove!");

        shoppingCart.add(1, new Product(APPLE, 5));
        shoppingCart.remove(2, APPLE);
    }

    @Test
    public void should_get_total_price_without_discount() throws Exception {
        shoppingCart.add(2, new Product(PEAR, 5));

        assertThat(shoppingCart.getPromotionPrice(), is(10.0));
    }

    @Test
    public void should_get_total_price_with_discount() throws Exception {
        Product pearWithDiscount = new Product(PEAR, 5).with(new Discount(5));
        pearWithDiscount.promote();
        shoppingCart.add(2, pearWithDiscount);

        assertThat(shoppingCart.getPromotionPrice(), is(5.0));
    }

    @Test
    public void should_get_total_price_with_second_half_discount() throws Exception {
        Product pearWithDiscount = new Product(PEAR, 10).with(new SecondHalf(3));
        pearWithDiscount.promote();
        shoppingCart.add(3, pearWithDiscount);

        assertThat(shoppingCart.getPromotionPrice(), is(25.0));
    }

    @Test
    public void should_get_total_price_with_two_discounts() throws Exception {
        Product pearWithDiscountAndSecondHalf = new Product(PEAR, 10)
                .with(new SecondHalf(3))
                .with(new Discount(5));

        pearWithDiscountAndSecondHalf.promote();
        shoppingCart.add(3, pearWithDiscountAndSecondHalf);

        assertThat(shoppingCart.getPromotionPrice(), closeTo(12.5, 0.1));
    }

    @Test
    public void should_get_the_same_total_price_with_two_discounts_but_the_order_is_reversed() throws Exception {
        Product pearWithDiscountAndSecondHalf = new Product(PEAR, 10)
                .with(new Discount(5))
                .with(new SecondHalf(3));

        pearWithDiscountAndSecondHalf.promote();
        shoppingCart.add(3, pearWithDiscountAndSecondHalf);

        assertThat(shoppingCart.getPromotionPrice(), closeTo(12.5, 0.1));
    }

    @Test
    public void should_get_total_price_with_second_half_and_discount_in_multiple_kind_of_products() throws Exception {
        Product pearWithDiscountAndSecondHalf = new Product(PEAR, 10)
                .with(new Discount(7.5))
                .with(new SecondHalf(5));
        pearWithDiscountAndSecondHalf.promote();

        Product pearWithDiscount = new Product(PEAR, 10).with(new SecondHalf(3));
        pearWithDiscount.promote();

        shoppingCart.add(5, pearWithDiscountAndSecondHalf);
        shoppingCart.add(3, pearWithDiscount);

        assertThat(shoppingCart.getPromotionPrice(), closeTo(55, 0.1));
    }

    @Test
    public void should_get_total_price_with_reduce_x_yuan_when_reaching_100() throws Exception {
        Product pear = new Product(ProductName.PEAR, 10);

        shoppingCart.add(10, pear, new ReduceXUponReachingY(5, 100));

        assertThat(shoppingCart.getPromotionPrice(), closeTo(95, 0.1));
    }

    @Test
    public void should_get_total_price_for_shopping_cart_with_reduce_x_yuan_when_reaching_100() throws Exception {
        Product pear = new Product(ProductName.PEAR, 10);

        shoppingCart.add(11, pear, new ReduceXUponReachingY(5, 100)).with(new ReduceXUponReachingY(3, 100)).promote();

        assertThat(shoppingCart.getPromotionPrice(), closeTo(102, 0.1));
    }
}
