package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;
import com.prestashop.base.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartPage extends BasePage {

    private final SelenideElement shoppingCartTitle = $x("//div[@class='card-block']/h1");
    private final SelenideElement proceedToCheckoutButton = $x("//div[contains(@class,'cart-summary')]//a");

    @Override
    @Step("Waiting for the Shopping Cart page to load")
    public void waitForPageLoad() {
        shoppingCartTitle.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    @Step("Proceed to order confirmation")
    public PersonalInformationPage proceedToOrderConfirmation() {
        proceedToCheckoutButton.click();
        return new PersonalInformationPage();
    }

}
