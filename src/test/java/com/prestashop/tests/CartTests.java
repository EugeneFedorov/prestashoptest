package com.prestashop.tests;

import com.prestashop.model.Customer;
import com.prestashop.pages.OrderConfirmationPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends BaseTest {

    @Test
    public void testAddMenClothesToCart() {
        OrderConfirmationPage orderConfirmationPage = getHomePage()
                .getClothesPage()
                .getMenClothes()
                .getFirstSampleOfClothes()
                .addProductToCart()
                .continueShopping()
                .returnToHomePage()
                .getArtPage()
                .getFirstSampleOfArts()
                .addProductToCart()
                .proceedToCheckout()
                .proceedToOrderConfirmation()
                .fillCustomerInfo(Customer.getCustomer());
        boolean isOrderSuccessful = orderConfirmationPage.isOrderSuccessful();
        assertTrue(isOrderSuccessful, "Failed to order the product!");
    }

}
