package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ArtPage extends BasePage {

    private final SelenideElement artTitle = $x("//div[@id='js-product-list-header']//h1");
    private final SelenideElement menSubCategory = $x("//a[@title='Men']");
    private final SelenideElement womenSubCategory = $x("//a[@title='Women']");

    @Override
    @Step("Checking that the 'Art' page is loaded")
    public void waitForPageLoad() {
        artTitle.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    public ProductCardPage getFirstSampleOfArts() {
        productsFromThePage.get(0).click();
        return new ProductCardPage();
    }

}
