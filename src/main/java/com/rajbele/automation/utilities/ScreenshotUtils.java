package com.rajbele.automation.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils class - Provides screenshot capture functionality
 * Saves screenshots with timestamp to configured directory
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "screenshots/";

    /**
     * Takes a screenshot and saves it with a timestamp
     *
     * @param driver WebDriver instance
     * @param screenshotName Name for the screenshot
     * @return Path where screenshot is saved
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create screenshots directory if it doesn't exist
            File screenshotsFolder = new File(SCREENSHOT_DIR);
            if (!screenshotsFolder.exists()) {
                screenshotsFolder.mkdirs();
            }

            // Generate unique filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = screenshotName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + filename;

            // Capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);

            // Copy screenshot to destination
            FileUtils.copyFile(srcFile, destFile);

            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Takes a screenshot with default naming
     *
     * @param driver WebDriver instance
     * @return Path where screenshot is saved
     */
    public static String takeScreenshot(WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return takeScreenshot(driver, "Screenshot_" + timestamp);
    }
}
