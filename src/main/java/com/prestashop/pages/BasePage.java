package com.prestashop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.prestashop.utils.ConfigManager;
import com.prestashop.utils.ScreenshotUtils;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;

public abstract class BasePage {

    protected static final int DEFAULT_TIMEOUT = ConfigManager.getElementTimeout();
    protected final ElementsCollection productsFromThePage = $$x("//div/h2/a");

    public abstract void waitForPageLoad();

    @Step("Take a screenshot")
    public void takeScreenshot(String screenshotName) {
        ScreenshotUtils.takeScreenshot(screenshotName);
    }

    @Step("Waiting for element: {element}")
    protected void waitForElement(SelenideElement element) {
        element.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

}
