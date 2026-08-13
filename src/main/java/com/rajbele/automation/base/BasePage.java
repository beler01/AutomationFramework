package com.rajbele.automation.base;

import com.rajbele.automation.utilities.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.time.Duration;

/**
 * BasePage class - Base class for all Page Object classes
 * Provides common locator interactions and helper methods
 */
public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    /**
     * Constructor for BasePage
     * Initializes the WebDriver and WaitUtils
     * Initializes PageFactory for Page Object Model
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the WebDriver instance
     *
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Gets the current page title
     *
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Gets the current page URL
     *
     * @return Page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Navigates to a specific URL
     *
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Goes back in browser history
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Goes forward in browser history
     */
    public void goForward() {
        driver.navigate().forward();
    }

    /**
     * Refreshes the current page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Gets WaitUtils instance
     *
     * @return WaitUtils instance
     */
    public WaitUtils getWaitUtils() {
        return waitUtils;
    }

    /**
     * Finds a single element by locator
     *
     * @param by the locator
     * @return WebElement
     */
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    /**
     * Finds multiple elements by locator
     *
     * @param by the locator
     * @return List of WebElements
     */
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    /**
     * Clicks on an element
     *
     * @param by the locator
     */
    public void clickElement(By by) {
        waitUtils.waitForElementToBeClickable(by);
        findElement(by).click();
    }

    /**
     * Clicks on a WebElement
     *
     * @param element the WebElement to click
     */
    public void clickElement(WebElement element) {
        element.click();
    }

    /**
     * Types text into an element
     *
     * @param by the locator
     * @param text the text to type
     */
    public void typeText(By by, String text) {
        waitUtils.waitForElementToBeVisible(by);
        WebElement element = findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Types text into a WebElement
     *
     * @param element the WebElement
     * @param text the text to type
     */
    public void typeText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Gets text from an element
     *
     * @param by the locator
     * @return the text content
     */
    public String getText(By by) {
        waitUtils.waitForElementToBeVisible(by);
        return findElement(by).getText();
    }

    /**
     * Gets text from a WebElement
     *
     * @param element the WebElement
     * @return the text content
     */
    public String getText(WebElement element) {
        return element.getText();
    }

    /**
     * Gets attribute value from an element
     *
     * @param by the locator
     * @param attributeName the attribute name
     * @return the attribute value
     */
    public String getAttribute(By by, String attributeName) {
        waitUtils.waitForElementToBeVisible(by);
        return findElement(by).getAttribute(attributeName);
    }

    /**
     * Gets attribute value from a WebElement
     *
     * @param element the WebElement
     * @param attributeName the attribute name
     * @return the attribute value
     */
    public String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    /**
     * Clears text from an input element
     *
     * @param by the locator
     */
    public void clearText(By by) {
        WebElement element = findElement(by);
        element.clear();
    }

    /**
     * Checks if an element is displayed
     *
     * @param by the locator
     * @return true if displayed, false otherwise
     */
    public boolean isElementDisplayed(By by) {
        try {
            return findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if an element is enabled
     *
     * @param by the locator
     * @return true if enabled, false otherwise
     */
    public boolean isElementEnabled(By by) {
        try {
            return findElement(by).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if an element is selected
     *
     * @param by the locator
     * @return true if selected, false otherwise
     */
    public boolean isElementSelected(By by) {
        try {
            return findElement(by).isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Selects an option from a dropdown by text
     *
     * @param by the locator of the dropdown
     * @param text the text to select
     */
    public void selectDropdownByText(By by, String text) {
        waitUtils.waitForElementToBeVisible(by);
        Select select = new Select(findElement(by));
        select.selectByVisibleText(text);
    }

    /**
     * Selects an option from a dropdown by value
     *
     * @param by the locator of the dropdown
     * @param value the value to select
     */
    public void selectDropdownByValue(By by, String value) {
        waitUtils.waitForElementToBeVisible(by);
        Select select = new Select(findElement(by));
        select.selectByValue(value);
    }

    /**
     * Gets all selected options from a dropdown
     *
     * @param by the locator of the dropdown
     * @return List of selected WebElements
     */
    public List<WebElement> getSelectedOptions(By by) {
        Select select = new Select(findElement(by));
        return select.getAllSelectedOptions();
    }

    /**
     * Waits for visibility of element
     *
     * @param by the locator
     */
    public void waitForVisibility(By by) {
        waitUtils.waitForElementToBeVisible(by);
    }

    /**
     * Waits for presence of element
     *
     * @param by the locator
     */
    public void waitForPresence(By by) {
        waitUtils.waitForElementToBePresent(by);
    }

    /**
     * Waits for URL to contain string
     *
     * @param urlPortion the URL portion to wait for
     */
    public void waitForUrlToContain(String urlPortion) {
        waitUtils.waitForUrlToContain(urlPortion);
    }

    /**
     * Waits for title to contain string
     *
     * @param titlePortion the title portion to wait for
     */
    public void waitForTitleToContain(String titlePortion) {
        waitUtils.waitForTitleToContain(titlePortion);
    }

    /**
     * Waits for invisibility of element
     *
     * @param by the locator
     */
    public void waitForInvisibility(By by) {
        waitUtils.waitForElementToBeInvisible(by);
    }

    /**
     * Waits for page to load
     */
    public void waitForPageLoad() {
        try {
            // Wait for jQuery to be ready if present
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until((d) -> (Boolean) executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            // If jQuery not present or timeout, continue
        }
    }

    /**
     * Executes JavaScript
     *
     * @param script the script to execute
     * @param args the arguments
     * @return the result of script execution
     */
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }

    /**
     * Scrolls to an element
     *
     * @param by the locator
     */
    public void scrollToElement(By by) {
        WebElement element = findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls to a WebElement
     *
     * @param element the WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Hovers over an element
     *
     * @param by the locator
     */
    public void hoverOverElement(By by) {
        waitUtils.waitForElementToBeVisible(by);
        WebElement element = findElement(by);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Double clicks on an element
     *
     * @param by the locator
     */
    public void doubleClickElement(By by) {
        waitUtils.waitForElementToBeClickable(by);
        WebElement element = findElement(by);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.doubleClick(element).perform();
    }

    /**
     * Right clicks on an element
     *
     * @param by the locator
     */
    public void rightClickElement(By by) {
        waitUtils.waitForElementToBeClickable(by);
        WebElement element = findElement(by);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.contextClick(element).perform();
    }

    /**
     * Drags and drops an element
     *
     * @param sourceBy the source element locator
     * @param targetBy the target element locator
     */
    public void dragAndDrop(By sourceBy, By targetBy) {
        WebElement sourceElement = findElement(sourceBy);
        WebElement targetElement = findElement(targetBy);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    /**
     * Switches to frame by index
     *
     * @param frameIndex the frame index
     */
    public void switchToFrameByIndex(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }

    /**
     * Switches to frame by locator
     *
     * @param by the locator
     */
    public void switchToFrameByLocator(By by) {
        driver.switchTo().frame(findElement(by));
    }

    /**
     * Switches to default content
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Switches to parent frame
     */
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    /**
     * Gets current window handle
     *
     * @return the window handle
     */
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * Gets all window handles
     *
     * @return Set of window handles
     */
    public java.util.Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * Switches to window by handle
     *
     * @param windowHandle the window handle
     */
    public void switchToWindowByHandle(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    /**
     * Ad-safe click method that handles ad overlays
     * Uses JavaScript click and retries on ElementClickIntercepted
     *
     * @param by the locator
     */
    public void clickElementAdSafe(By by) {
        try {
            waitUtils.waitForElementToBeClickableAndAccessible(by);
            WebElement element = findElement(by);
            
            // First, scroll to element and try JavaScript click
            scrollToElement(element);
            waitUtils.sleep(300); // Give time for ads to fully load
            
            try {
                // Try JavaScript click first (bypasses overlay issues)
                executeScript("arguments[0].click();", element);
            } catch (Exception e) {
                // If JavaScript click fails, try regular click with retry
                clickWithRetry(element);
            }
        } catch (ElementClickInterceptedException e) {
            // If intercepted even after ad wait, retry with JavaScript
            try {
                WebElement element = findElement(by);
                executeScript("arguments[0].click();", element);
            } catch (Exception retryE) {
                throw new RuntimeException("Failed to click element even after ad handling: " + by);
            }
        }
    }

    /**
     * Ad-safe click method for WebElement
     * Uses JavaScript click and retries on ElementClickIntercepted
     *
     * @param element the WebElement to click
     */
    public void clickElementAdSafe(WebElement element) {
        try {
            // Scroll to element first
            scrollToElement(element);
            waitUtils.sleep(300); // Give time for ads to fully load
            
            try {
                // Try JavaScript click first
                executeScript("arguments[0].click();", element);
            } catch (Exception e) {
                // If JavaScript click fails, try regular click with retry
                clickWithRetry(element);
            }
        } catch (ElementClickInterceptedException e) {
            // If intercepted, retry with JavaScript
            try {
                executeScript("arguments[0].click();", element);
            } catch (Exception retryE) {
                throw new RuntimeException("Failed to click element even after ad handling");
            }
        }
    }

    /**
     * Helper method to retry click with exponential backoff
     *
     * @param element the WebElement to click
     */
    private void clickWithRetry(WebElement element) {
        int maxAttempts = 3;
        for (int i = 0; i < maxAttempts; i++) {
            try {
                element.click();
                return; // Success
            } catch (ElementClickInterceptedException e) {
                if (i < maxAttempts - 1) {
                    // Wait before retry (exponential backoff)
                    waitUtils.sleep(500 * (i + 1));
                    
                    // Try to scroll again and dismiss ads
                    try {
                        scrollToElement(element);
                    } catch (Exception scrollE) {
                        // Continue anyway
                    }
                } else {
                    throw e; // Last attempt failed
                }
            }
        }
    }

    /**
     * Waits for ad iframes to disappear before interacting with element
     * Useful as a preprocessing step before clicks
     */
    public void waitForAdsToDisappear() {
        waitUtils.waitForAdIframesToDisappear();
    }
}

