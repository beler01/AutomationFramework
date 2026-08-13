package com.rajbele.automation.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

/**
 * CommonUtils class - Provides common utility methods
 * Includes JavaScript execution, scrolling, and other common operations
 */
public class CommonUtils {

    /**
     * Scrolls to a specific element on the page
     *
     * @param driver WebDriver instance
     * @param element WebElement to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls to the top of the page
     *
     * @param driver WebDriver instance
     */
    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls to the bottom of the page
     *
     * @param driver WebDriver instance
     */
    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls by specified pixels
     *
     * @param driver WebDriver instance
     * @param pixelsToScroll Number of pixels to scroll
     */
    public static void scrollByPixels(WebDriver driver, int pixelsToScroll) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + pixelsToScroll + ");");
    }

    /**
     * Highlights an element on the page
     *
     * @param driver WebDriver instance
     * @param element WebElement to highlight
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    /**
     * Executes JavaScript and returns result
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
     * Gets text of all elements matching a locator
     *
     * @param elements List of WebElements
     * @return List of text from elements
     */
    public static List<String> getTextOfElements(List<WebElement> elements) {
        List<String> textList = new java.util.ArrayList<>();
        for (WebElement element : elements) {
            textList.add(element.getText());
        }
        return textList;
    }

    /**
     * Checks if element is displayed on page
     *
     * @param element WebElement to check
     * @return true if element is displayed
     */
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if element is enabled
     *
     * @param element WebElement to check
     * @return true if element is enabled
     */
    public static boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets count of elements
     *
     * @param elements List of WebElements
     * @return Count of elements
     */
    public static int getElementCount(List<WebElement> elements) {
        return elements.size();
    }
}
