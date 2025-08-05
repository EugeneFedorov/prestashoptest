package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class OrderConfirmationPage extends BasePage {

    private final String CONFIRMATION_TEXT = "YOUR ORDER IS CONFIRMED";
    private final SelenideElement confirmationTitle = $x("//section[@id='content-hook_order_confirmation']//h3");

    @Override
    public void waitForPageLoad() {
        confirmationTitle.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    public boolean isOrderSuccessful() {
        return CONFIRMATION_TEXT.equals(confirmationTitle.getText().substring(1));
    }

}
