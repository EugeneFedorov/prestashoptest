package com.prestashop.tests;

import com.prestashop.BaseTest;
import com.prestashop.model.Customer;
import com.prestashop.pages.OrderConfirmationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderFromCartTests extends BaseTest {

    @Test
    @Story("Cart functionality for online store")
    @Description("The user can add more than one product to the cart")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("The user can place an order from the cart.")
    public void testAddMenClothesAndArtToCart() {
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
