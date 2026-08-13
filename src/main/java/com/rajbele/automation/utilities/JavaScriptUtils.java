package com.rajbele.automation.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * JavaScriptUtils class - Provides reusable JavaScript execution methods
 * Handles JavaScript-based interactions and DOM manipulation
 */
public class JavaScriptUtils {

    /**
     * Executes JavaScript code and returns the result
     *
     * @param driver WebDriver instance
     * @param script JavaScript code to execute
     * @param args Arguments for the script
     * @return Result of JavaScript execution
     */
    public static Object executeJavaScript(WebDriver driver, String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }

    /**
     * Performs JavaScript click on an element
     * Useful when standard click() doesn't work or causes issues
     *
     * @param driver WebDriver instance
     * @param element WebElement to click
     */
    public static void jsClick(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls the page to a specific element
     * Brings the element into view
     *
     * @param driver WebDriver instance
     * @param element WebElement to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls the page to the top
     *
     * @param driver WebDriver instance
     */
    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls the page to the bottom
     *
     * @param driver WebDriver instance
     */
    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls the page by specified pixels
     *
     * @param driver WebDriver instance
     * @param pixels Number of pixels to scroll (positive = down, negative = up)
     */
    public static void scrollByPixels(WebDriver driver, int pixels) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    /**
     * Highlights an element on the page (useful for debugging)
     * Adds a red border to the element
     *
     * @param driver WebDriver instance
     * @param element WebElement to highlight
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    /**
     * Gets the page title using JavaScript
     *
     * @param driver WebDriver instance
     * @return Page title
     */
    public static String getPageTitle(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.title;");
    }

    /**
     * Gets the current page URL using JavaScript
     *
     * @param driver WebDriver instance
     * @return Current page URL
     */
    public static String getPageUrl(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return window.location.href;");
    }

    /**
     * Sets the value of an input element using JavaScript
     * Useful when sendKeys() doesn't work properly
     *
     * @param driver WebDriver instance
     * @param element Input element
     * @param value Value to set
     */
    public static void setInputValue(WebDriver driver, WebElement element, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + value + "';", element);
    }

    /**
     * Checks if an element is visible on the page
     *
     * @param driver WebDriver instance
     * @param element WebElement to check
     * @return true if element is visible, false otherwise
     */
    public static boolean isElementVisible(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (Boolean) jsExecutor.executeScript(
            "return typeof(element) != 'undefined' && element.offsetParent != null;", element
        );
    }

    /**
     * Removes an element from the DOM
     * Useful for removing overlays, popups, etc.
     *
     * @param driver WebDriver instance
     * @param element WebElement to remove
     */
    public static void removeElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].remove();", element);
    }

    /**
     * Gets the text content of an element using JavaScript
     *
     * @param driver WebDriver instance
     * @param element WebElement
     * @return Text content
     */
    public static String getTextContent(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
    }

    /**
     * Gets the inner HTML of an element
     *
     * @param driver WebDriver instance
     * @param element WebElement
     * @return Inner HTML
     */
    public static String getInnerHTML(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].innerHTML;", element);
    }

    /**
     * Waits for jQuery (if available) to complete all AJAX requests
     * Useful for applications using jQuery
     *
     * @param driver WebDriver instance
     */
    public static void waitForJQueryToComplete(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("return jQuery.active == 0");
    }
}
