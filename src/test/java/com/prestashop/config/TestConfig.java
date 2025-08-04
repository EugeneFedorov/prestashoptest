package com.prestashop.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.prestashop.utils.ConfigManager;
import com.prestashop.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            setupAllure();
            logger.info("Test configuration initialized successfully");
        } catch (Exception exception) {
            logger.error("Failed to initialize test configuration", exception);
            throw exception;
        }
    }

    private void setupSelenide() {
        // Check if Selenoid is needed for testing
        boolean useSelenoid = Boolean.parseBoolean(System.getProperty("selenoid", "false"));

        if (useSelenoid) {
            logger.info("Configuring Selenoid environment");
            setupSelenoid();
        } else {
            logger.info("Configuring local browser environment");
            setupLocalBrowser();
        }

        // General Selenide settings
        Configuration.timeout = ConfigManager.getDefaultTimeout();
        Configuration.pageLoadTimeout = ConfigManager.getPageLoadTimeout();
        Configuration.reportsFolder = "target/selenide-reports";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

        logger.debug("Selenide configuration: timeout={}ms, pageLoadTimeout={}ms, reportsFolder={}",
                Configuration.timeout, Configuration.pageLoadTimeout, Configuration.reportsFolder);
    }

    private void setupSelenoid() {
        // Settings for Selenoid
        String selenoidUrl = System.getProperty("selenoid.url", ConfigManager.getSelenoidUrl());
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browser.version", "latest");

        Configuration.remote = selenoidUrl;
        Configuration.browser = browser;
        Configuration.browserSize = ConfigManager.getBrowserSize();
        Configuration.headless = false;

        logger.info("Selenoid configuration: url={}, browser={}, version={}, size={}",
                Configuration.remote, Configuration.browser, browserVersion, Configuration.browserSize);

        // Setting up capabilities for Selenoid
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // General capabilities for Selenoid
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("enableLog", true);
        capabilities.setCapability("logName", "selenoid.log");
        capabilities.setCapability("videoName", "selenoid.mp4");
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("timeZone", "Europe/Moscow");
        capabilities.setCapability("locale", "ru_RU");

        // Specific settings for different browsers
        logger.debug("Configuring {} capabilities", browser);
        switch (browser) {
            case CHROME -> {
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", browserVersion);
                capabilities.setCapability("goog:chromeOptions",
                        Map.of("args", List.of("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu")));
            }
            case FIREFOX -> {
                capabilities.setCapability("browserName", "firefox");
                capabilities.setCapability("browserVersion", browserVersion);
                capabilities.setCapability("marionette", true);
            }
            case EDGE -> {
                capabilities.setCapability("browserName", "MicrosoftEdge");
                capabilities.setCapability("browserVersion", browserVersion);
            }
            default -> logger.warn("Unsupported browser type: {}", browser);
        }

        Configuration.browserCapabilities = capabilities;
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

            default ->
                    logger.debug("No special configuration needed for browser: {}", Configuration.browser);
        }

    }

    private void setupAllure() {
        try {
            Files.createDirectories(Paths.get("target/allure-results"));
        } catch (IOException ioException) {
            System.err.println("Failed to create allure-results directory: " + ioException.getMessage());
        }
    }

    public static void openUrl(String url) {
        Selenide.open(url);
        Allure.addAttachment("URL", "text/plain", url);
    }

    public static void takeScreenshot(String screenshotName) {
        ScreenshotUtils.takeScreenshot(screenshotName);
    }

}
