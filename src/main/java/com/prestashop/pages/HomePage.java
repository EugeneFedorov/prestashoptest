package com.prestashop.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.title;

public class HomePage extends BasePage {

    private static final String FRAME_NAME = "framelive";
    private final SelenideElement loader = Selenide.$x("//div[@id='loadingMessage']");
    private final SelenideElement logo = $x("//div[@id='logo']");
    private final SelenideElement logoMyStore = $x("//img[@alt='PrestaShop']");
    private final SelenideElement searchInput = $x("//input[@aria-label='Search']");
    private final SelenideElement clothesLink = $x("//li[@id='category-3']");
    private final SelenideElement menClothesLink = $x("//li[@id='category-4']");
    private final SelenideElement womenClothesLink = $x("//li[@id='category-5']");
    private final SelenideElement accessoriesLink = $x("//li[@id='category-6']");
    private final SelenideElement stationeryAccessoriesLink = $x("//li[@id='category-7']");
    private final SelenideElement homeAccessoriesLink = $x("//li[@id='category-8']");
    private final SelenideElement artLink = $x("//li[@id='category-9']");

    @Override
    @Step("Waiting for the Home page to load")
    public void waitForPageLoad() {
        logo.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        loader.shouldBe(Condition.disappear, Duration.ofMillis(DEFAULT_TIMEOUT));
        switchToFrame();
        searchInput.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
    }

    private void switchToFrame() {
        Selenide.webdriver().driver().switchTo().frame(FRAME_NAME);
    }

    private void switchToDefaultContent() {
        Selenide.webdriver().driver().switchTo().defaultContent();
    }

    @Step("Checking that the 'Home' page is loaded")
    public boolean isPageLoaded() {
        return logoMyStore.is(visible) && searchInput.is(visible);
    }

    @Step("Getting the Page Title")
    public String getPageTitle() {
        return title();
    }

    @Step("Getting the 'Clothes' page")
    public ClothesPage getClothesPage() {
        clothesLink.click();
        return new ClothesPage();
    }

    @Step("Getting the 'Art' page")
    public ArtPage getArtPage() {
        artLink.click();
        return new ArtPage();
    }

}
