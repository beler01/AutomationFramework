package com.rajbele.automation.listeners;

import com.rajbele.automation.utilities.ExtentReportsManager;
import com.rajbele.automation.utilities.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNGListener class - Implements ITestListener for test event handling
 * Logs test start, success, failure, and skip events to Extent Reports
 */
public class TestNGListener implements ITestListener {

    /**
     * Called when test execution starts
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    /**
     * Called when test execution completes
     */
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        ExtentReportsManager.flush();
    }

    /**
     * Called when test starts
     */
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        
        System.out.println("Test Started: " + testName);
        ExtentReportsManager.createTest(testName, testDescription);
        ExtentReportsManager.logInfo("Test Method: " + testName);
    }

    /**
     * Called when test passes
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test Passed: " + testName);
        ExtentReportsManager.logPass("Test Passed: " + testName);
    }

    /**
     * Called when test fails
     */
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test Failed: " + testName);
        ExtentReportsManager.logFail("Test Failed: " + testName);

        // Try to take screenshot
        try {
            Object testInstance = result.getInstance();
            if (testInstance != null) {
                java.lang.reflect.Field driverField = testInstance.getClass().getSuperclass().getDeclaredField("driver");
                driverField.setAccessible(true);
                WebDriver driver = (WebDriver) driverField.get(testInstance);

                if (driver != null) {
                    String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName + "_Failed");
                    ExtentReportsManager.attachScreenshot(screenshotPath);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not capture screenshot on test failure: " + e.getMessage());
        }

        // Log failure cause
        ExtentReportsManager.logError("Failure Cause: " + result.getThrowable());
    }

    /**
     * Called when test is skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test Skipped: " + testName);
        ExtentReportsManager.logWarning("Test Skipped: " + testName);
    }

    /**
     * Called when test fails within success percentage
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within success percentage: " + result.getMethod().getMethodName());
    }
}
