package com.rajbele.automation.base;

import com.rajbele.automation.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * DriverFactory class for creating and managing WebDriver instances
 * Uses ThreadLocal to ensure thread-safe WebDriver management for parallel execution
 * Supports Chrome, Firefox, and Edge browsers with headless mode option
 */
public class DriverFactory {

    // ThreadLocal to maintain separate WebDriver instance per thread
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Gets the WebDriver instance for the current thread
     *
     * @return WebDriver instance from ThreadLocal
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Creates and sets a WebDriver instance for the current thread
     * Reads browser configuration from config.properties
     *
     * @return WebDriver instance
     */
    public static WebDriver initializeDriver() {
        String browser = ConfigManager.getProperty("browser", "chrome");
        WebDriver driver = createDriver(browser);
        driverThreadLocal.set(driver);
        return driver;
    }

    /**
     * Creates a WebDriver instance based on browser configuration
     * Applies timeouts based on config.properties settings
     *
     * @param browser Browser type (chrome, firefox, edge)
     * @return WebDriver instance
     */
    private static WebDriver createDriver(String browser) {
        WebDriver driver;
        boolean isHeadless = ConfigManager.getBooleanProperty("headless", false);

        switch (browser.toLowerCase().trim()) {
            case "chrome":
                driver = createChromeDriver(isHeadless);
                break;
            case "firefox":
                driver = createFirefoxDriver(isHeadless);
                break;
            case "edge":
                driver = createEdgeDriver(isHeadless);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Set implicit wait timeout
        int implicitWait = ConfigManager.getIntProperty("implicitWait", 0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        return driver;
    }

    /**
     * Creates Chrome WebDriver with specified options
     * Applies Chrome-specific configurations and headless settings
     */
    private static WebDriver createChromeDriver(boolean isHeadless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Headless mode configuration
        if (isHeadless) {
            options.addArguments("--headless=new");
        }

        // Chrome-specific arguments
        options.addArguments(
            "--disable-gpu",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--disable-popup-blocking",
            "--disable-notifications"
        );

        return new ChromeDriver(options);
    }

    /**
     * Creates Firefox WebDriver with specified options
     * Applies Firefox-specific configurations and headless settings
     */
    private static WebDriver createFirefoxDriver(boolean isHeadless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        // Headless mode configuration
        if (isHeadless) {
            options.addArguments("--headless");
        }

        // Firefox-specific arguments
        options.addArguments(
            "--disable-popup",
            "--disable-notifications"
        );

        return new FirefoxDriver(options);
    }

    /**
     * Creates Edge WebDriver with specified options
     * Applies Edge-specific configurations and headless settings
     */
    private static WebDriver createEdgeDriver(boolean isHeadless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        // Headless mode configuration
        if (isHeadless) {
            options.addArguments("--headless");
        }

        // Edge-specific arguments
        options.addArguments(
            "--disable-gpu",
            "--no-sandbox",
            "--disable-dev-shm-usage"
        );

        return new EdgeDriver(options);
    }

    /**
     * Quits the WebDriver instance for the current thread
     * Removes the driver from ThreadLocal to free resources
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    /**
     * Closes all driver instances (for cleanup)
     */
    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while closing driver: " + e.getMessage());
            }
        }
    }
}
