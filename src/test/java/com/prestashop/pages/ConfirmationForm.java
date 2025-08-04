package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ConfirmationForm extends ProductCardPage {

    private final SelenideElement continueShoppingButton = $x("//div[@class='cart-content']//button");
    private final SelenideElement proceedToCheckoutButton = $x("//div[@class='cart-content']//a");

    @Step("Stay on the current product page for continue shopping")
    public ProductCardPage continueShopping() {
        continueShoppingButton.click();
        return new ProductCardPage();
    }

    @Step("Navigate to the cart for finalize order")
    public ShoppingCartPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new ShoppingCartPage();
    }

}
