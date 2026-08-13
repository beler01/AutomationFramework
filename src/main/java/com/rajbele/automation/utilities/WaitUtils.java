package com.rajbele.automation.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.rajbele.automation.constants.TimeoutConstants;

import java.time.Duration;

/**
 * WaitUtils class - Provides wait utility methods for explicit waits
 * Uses WebDriverWait for various wait scenarios
 */
public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait defaultWait;
    private WebDriverWait longWait;
    private WebDriverWait shortWait;

    /**
     * Constructor for WaitUtils
     *
     * @param driver WebDriver instance
     */
    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConstants.EXPLICIT_WAIT));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConstants.LONG_WAIT));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConstants.SHORT_WAIT));
    }

    /**
     * Waits for element to be visible
     *
     * @param locator By locator of the element
     * @return WebElement when visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return defaultWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for element to be visible with custom timeout
     *
     * @param locator By locator of the element
     * @param seconds Timeout in seconds
     * @return WebElement when visible
     */
    public WebElement waitForElementToBeVisible(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for element to be clickable
     *
     * @param locator By locator of the element
     * @return WebElement when clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for element to be clickable with custom timeout
     *
     * @param locator By locator of the element
     * @param seconds Timeout in seconds
     * @return WebElement when clickable
     */
    public WebElement waitForElementToBeClickable(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for element to be present in DOM
     *
     * @param locator By locator of the element
     * @return WebElement when present
     */
    public WebElement waitForElementToBePresent(By locator) {
        return defaultWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for element to be present with custom timeout
     *
     * @param locator By locator of the element
     * @param seconds Timeout in seconds
     * @return WebElement when present
     */
    public WebElement waitForElementToBePresent(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for element to be invisible
     *
     * @param locator By locator of the element
     * @return true if element becomes invisible
     */
    public boolean waitForElementToBeInvisible(By locator) {
        return defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits for text to be present in element
     *
     * @param locator By locator of the element
     * @param text Text to wait for
     * @return true if text is present
     */
    public boolean waitForTextToBePresentInElement(By locator, String text) {
        return defaultWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Waits for title to contain text
     *
     * @param title Title text to wait for
     * @return true if title contains text
     */
    public boolean waitForTitleToContain(String title) {
        return defaultWait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Waits for URL to contain text
     *
     * @param urlFraction URL fragment to wait for
     * @return true if URL contains fragment
     */
    public boolean waitForUrlToContain(String urlFraction) {
        return defaultWait.until(ExpectedConditions.urlContains(urlFraction));
    }

    /**
     * Uses long wait timeout
     *
     * @param locator By locator of the element
     * @return WebElement when visible
     */
    public WebElement longWaitForElementToBeVisible(By locator) {
        return longWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Uses short wait timeout
     *
     * @param locator By locator of the element
     * @return WebElement when visible
     */
    public WebElement shortWaitForElementToBeVisible(By locator) {
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for ad iframes to disappear (common issue with Google ads)
     * Checks for common ad iframe IDs and waits for them to be invisible
     *
     * @return true if ads are gone or don't exist
     */
    public boolean waitForAdIframesToDisappear() {
        try {
            // Wait for common ad iframes to become invisible
            String[] adFrameIds = {"aswift_0", "aswift_1", "aswift_2", "google_ads_frame", "c_top_container"};
            
            for (String frameId : adFrameIds) {
                try {
                    By adLocator = By.id(frameId);
                    if (!driver.findElements(adLocator).isEmpty()) {
                        shortWait.until(ExpectedConditions.invisibilityOfElementLocated(adLocator));
                    }
                } catch (Exception e) {
                    // Frame doesn't exist, continue to next
                }
            }
            return true;
        } catch (Exception e) {
            // If ad frames don't disappear, continue anyway
            return false;
        }
    }

    /**
     * Waits for element to be visible and not covered by ads
     * Combines visibility check with ad frame invisibility
     *
     * @param locator By locator of the element
     * @return WebElement when visible and accessible
     */
    public WebElement waitForElementToBeVisibleAndAccessible(By locator) {
        waitForAdIframesToDisappear();
        return waitForElementToBeVisible(locator);
    }

    /**
     * Waits for element to be clickable and not covered by ads
     * Combines clickability check with ad frame invisibility
     *
     * @param locator By locator of the element
     * @return WebElement when clickable and accessible
     */
    public WebElement waitForElementToBeClickableAndAccessible(By locator) {
        waitForAdIframesToDisappear();
        return waitForElementToBeClickable(locator);
    }

    /**
     * Thread sleep for specified milliseconds
     *
     * @param milliseconds Time to sleep in milliseconds
     */
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
