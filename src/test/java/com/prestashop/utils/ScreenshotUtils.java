package com.prestashop.utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class ScreenshotUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);

    public static void takeScreenshot(String screenshotName) {
        if (screenshotName == null || screenshotName.trim().isEmpty()) {
            logger.warn("Screenshot name cannot be null or empty");
            return;
        }

        try {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);

            if (screenshot == null || screenshot.length == 0) {
                logger.error("Failed to take screenshot - returned null or empty byte array");
                return;
            }

            Allure.addAttachment(
                    screenshotName,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png"
            );
            logger.debug("Successfully captured screenshot: {}", screenshotName);
        } catch (Exception e) {
            logger.error("Failed to take and attach screenshot '{}'", screenshotName, e);
        }
    }

}
