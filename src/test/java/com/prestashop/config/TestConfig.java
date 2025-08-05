package com.prestashop.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.prestashop.utils.ConfigManager;
import com.prestashop.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class TestConfig implements BeforeAllCallback {

    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        logger.info("Initializing test configuration...");
        try {
            setupSelenide();
            logger.info("Test configuration initialized successfully");
        } catch (Exception exception) {
            logger.error("Failed to initialize test configuration", exception);
            throw exception;
        }
    }

    private void setupSelenide() {
        logger.info("Configuring local browser environment");
        setupLocalBrowser();

        // General Selenide settings
        Configuration.timeout = ConfigManager.getDefaultTimeout();
        Configuration.pageLoadTimeout = ConfigManager.getPageLoadTimeout();
        Configuration.reportsFolder = "target/selenide-reports";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

        logger.debug("Selenide configuration: timeout={}ms, pageLoadTimeout={}ms, reportsFolder={}",
                Configuration.timeout, Configuration.pageLoadTimeout, Configuration.reportsFolder);
    }

    private void setupLocalBrowser() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = ConfigManager.getBrowserSize();
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless",
                String.valueOf(ConfigManager.isHeadless())));

        logger.info("Local browser configuration: browser={}, size={}, headless={}",
                Configuration.browser, Configuration.browserSize, Configuration.headless);

        switch (Configuration.browser) {
            case CHROME -> {
                logger.debug("Configuring Chrome options for local execution");
                Configuration.browserCapabilities.setCapability("goog:chromeOptions",
                        Map.of("args", List.of("--no-sandbox", "--disable-dev-shm-usage")));
            }

            case FIREFOX -> {
                logger.debug("Configuring Firefox options for local execution");
                Configuration.browserCapabilities.setCapability("marionette", true);
            }

            case EDGE ->
                //If necessary, a specific configuration can be added here.
                    logger.debug("Configuring Edge options for local execution");

            default -> logger.debug("No special configuration needed for browser: {}", Configuration.browser);
        }

    }

    public static void openUrl(String url) {
        Selenide.open(url);
        Allure.addAttachment("URL", "text/plain", url);
    }

}
