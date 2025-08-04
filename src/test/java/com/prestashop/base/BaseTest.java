package com.prestashop.base;

import com.prestashop.config.TestConfig;
import com.prestashop.pages.HomePage;
import com.prestashop.utils.ConfigManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestConfig.class)
public class BaseTest {

    @BeforeEach
    public void setUp() {
        TestConfig.openUrl(ConfigManager.getBaseUrl());
        new HomePage().waitForPageLoad();
    }

    public HomePage getHomePage() {
        return new HomePage();
    }

}
