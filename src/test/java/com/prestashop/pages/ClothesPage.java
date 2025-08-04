package com.prestashop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ClothesPage extends BasePage {

    private final SelenideElement clothesTitle = $x("//div[@id='js-product-list-header']");
    private final SelenideElement menSubCategory = $x("//a[@title='Men']");
    private final SelenideElement womenSubCategory = $x("//a[@title='Women']");

    @Override
    @Step("Checking that the 'Clothes' page is loaded")
    public void waitForPageLoad() {
        clothesTitle.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    public MenClothesPage getMenClothes() {
        menSubCategory.click();
        return new MenClothesPage();
    }

    public WomenClothesPage getWomenClothes() {
        womenSubCategory.click();
        return new WomenClothesPage();
    }

}
