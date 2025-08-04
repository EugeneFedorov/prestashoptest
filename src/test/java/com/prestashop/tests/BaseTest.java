package com.prestashop.tests;

import com.codeborne.selenide.Selenide;
import com.prestashop.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void setUp() {
        Selenide.open("https://demo.prestashop.com/");
        new HomePage().waitForPageLoad();
    }

    public HomePage getHomePage() {
        return new HomePage();
    }

}
