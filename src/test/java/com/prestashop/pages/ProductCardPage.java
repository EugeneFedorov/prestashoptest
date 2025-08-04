package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;
import com.prestashop.base.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProductCardPage extends BasePage {

    private final SelenideElement returnHomePageLink = $x("(//section[@id='wrapper']//li/a)[1]");
    private final SelenideElement contentWrapper = $x("//div[@id='content-wrapper']");
    private final SelenideElement addToCartButton = $x("//button[@data-button-action]");

    @Override
    @Step("Waiting for the Product cart page to load")
    public void waitForPageLoad() {
        contentWrapper.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    @Step("Add product to cart")
    public ConfirmationForm addProductToCart() {
        addToCartButton.click();
        return new ConfirmationForm();
    }

    @Step("Return to 'Home' page")
    public HomePage returnToHomePage() {
        returnHomePageLink.click();
        return new HomePage();
    }

}
