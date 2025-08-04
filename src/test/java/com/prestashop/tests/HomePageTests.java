package com.prestashop.tests;

import com.prestashop.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTests extends BaseTest {

    private final HomePage homePage = new HomePage();

    @Test
    @Story("Checking the loading of the main page")
    @Description("Check that the main page loads correctly")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("The home page should load correctly.")
    public void testHomePageLoadsCorrectly() {
        boolean isLoaded = homePage.isPageLoaded();
        assertTrue(isLoaded, "The home page must be loaded");
        assertNotNull(homePage.getPageTitle(), "Page title must not be empty");
        homePage.takeScreenshot("home-page-loaded");
    }

}
