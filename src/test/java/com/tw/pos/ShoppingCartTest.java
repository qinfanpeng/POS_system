package com.tw.pos;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.tw.pos.ProductName.PEAR;
import static com.tw.pos.ProductName.APPLE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        shoppingCart.add(1, new Product(APPLE, 5));

        assertThat(shoppingCart.getAmountOf(APPLE), is(1));
    }

    @Test
    public void should_add_products() throws Exception {
        shoppingCart.add(1, new Product(APPLE, 5));
        shoppingCart.add(2, new Product(PEAR, 7));

        assertThat(shoppingCart.getAmountOf(APPLE), is(1));
        assertThat(shoppingCart.getAmountOf(PEAR), is(2));
    }

    @Test
    public void should_throw_exception_when_add_zero_product() throws Exception {
        expectedException.expectMessage("Can't add (0)product");
        shoppingCart.add(0, new Product(APPLE, 5));
    }

    @Test
    public void should_remove_product() throws Exception {
        shoppingCart.add(1, new Product(APPLE, 5));
        shoppingCart.remove(1, APPLE);
        assertThat(shoppingCart.getAmountOf(APPLE), is(0));
    }

    @Test
    public void should_throw_exception_when_remove_a_non_exist_product() throws Exception {
        expectedException.expectMessage("Can't find any apple to remove!");
        shoppingCart.remove(1, APPLE);
    }

    @Test
    public void should_throw_exception_when_remove_more_than_exist_products_amount() throws Exception {
        expectedException.expectMessage("There're no 2 apples to remove!");
        shoppingCart.add(1, new Product(APPLE, 5));
        shoppingCart.remove(2, APPLE);
    }
}
