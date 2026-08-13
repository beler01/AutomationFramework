package com.rajbele.automation.base;

import com.rajbele.automation.config.ConfigManager;
import com.rajbele.automation.utilities.ExtentReportsManager;
import com.rajbele.automation.utilities.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest class - Base class for all test classes
 * Provides common setup and teardown for WebDriver initialization
 * Reads configuration from config.properties (browser, URL, headless mode)
 * Handles WebDriver lifecycle management
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Setup method - Runs before each test
     * - Initializes WebDriver using DriverFactory
     * - Navigates to the configured application URL
     * - Maximizes the browser window
     * - Logs test start information
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Initialize WebDriver from DriverFactory (reads browser config)
        driver = DriverFactory.initializeDriver();

        // Get application URL from config.properties
        String appUrl = ConfigManager.getProperty("url");
        if (appUrl == null || appUrl.trim().isEmpty()) {
            throw new RuntimeException("Application URL not configured in config.properties");
        }

        // Navigate to application URL
        driver.navigate().to(appUrl);

        // Maximize browser window
        driver.manage().window().maximize();

        // Log test start
        String browser = ConfigManager.getProperty("browser", "chrome");
        boolean isHeadless = ConfigManager.getBooleanProperty("headless", false);
        ExtentReportsManager.logInfo("Test started - Browser: " + browser + " | Headless: " + isHeadless);
    }

    /**
     * Teardown method - Runs after each test
     * - Quits WebDriver and removes from ThreadLocal
     * - Flushes Extent Reports
     * - Ensures clean state for next test
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                DriverFactory.quitDriver();
                ExtentReportsManager.logInfo("WebDriver closed");
            }
        } finally {
            ExtentReportsManager.flush();
        }
    }

    /**
     * Takes a screenshot and attaches it to the report
     *
     * @param screenshotName Name of the screenshot
     * @return Path of the screenshot
     */
    protected String captureScreenshot(String screenshotName) {
        return ScreenshotUtils.takeScreenshot(driver, screenshotName);
    }

    /**
     * Logs message to extent report
     */
    protected void logInfo(String message) {
        ExtentReportsManager.logInfo(message);
    }

    /**
     * Logs error message to extent report
     */
    protected void logError(String message) {
        ExtentReportsManager.logError(message);
    }

    /**
     * Logs pass message to extent report
     */
    protected void logPass(String message) {
        ExtentReportsManager.logPass(message);
    }
}
