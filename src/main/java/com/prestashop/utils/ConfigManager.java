package com.prestashop.utils;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_FILE = "test.properties";
    private static volatile boolean isInitialized = false;

    static {
        initialize();
    }

    @Step("Loading test configuration")
    private static synchronized void initialize() {
        if (!isInitialized) {
            loadProperties();
            isInitialized = true;
        }
    }

    private static void loadProperties() {
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                String errorMsg = String.format("Configuration file '%s' not found in classpath", CONFIG_FILE);
                logger.error(errorMsg);
                throw new IllegalStateException(errorMsg);
            }
            PROPERTIES.load(inputStream);
            logger.info("Successfully loaded {} configuration properties from {}", PROPERTIES.size(), CONFIG_FILE);
        } catch (IOException e) {
            String errorMsg = String.format("Failed to load configuration from '%s'", CONFIG_FILE);
            logger.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public static String getProperty(String key) {
        checkInitialization();
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in configuration", key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        checkInitialization();
        String value = PROPERTIES.getProperty(key, defaultValue);
        if (value.equals(defaultValue)) {
            logger.debug("Using default value '{}' for property '{}'", defaultValue, key);
        }
        return value;
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                logger.warn("Invalid integer value '{}' for key '{}', using default: {}", value, key, defaultValue, e);
            }
        }
        return defaultValue;
    }

    private static void checkInitialization() {
        if (!isInitialized) {
            throw new IllegalStateException("ConfigManager is not properly initialized");
        }
    }

    public static int getDefaultTimeout() {
        return getIntProperty("default.timeout", 10_000);
    }

    public static int getPageLoadTimeout() {
        return getIntProperty("page.load.timeout", 30_000);
    }

    public static String getBrowserSize() {
        return getProperty("browser.size", "1920x1080");
    }

    public static String getSelenoidUrl() {
        return getProperty("selenoid.url", "http://localhost:4444/wd/hub");
    }

    public static boolean isHeadless() {
        return getBooleanProperty("headless", false);
    }

    public static int getElementTimeout() {
        return getIntProperty("element.timeout", 5_000);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

}
