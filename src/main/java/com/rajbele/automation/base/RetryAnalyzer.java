package com.rajbele.automation.base;

import com.rajbele.automation.config.ConfigManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer class - Implements IRetryAnalyzer for automatic test retry on failure
 * Reads retry count from config.properties
 * Retries a test based on retry count configuration
 * Clearly logs each retry attempt
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = ConfigManager.getRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            String testName = result.getMethod().getMethodName();
            System.out.println("Retrying test: " + testName + " | Retry attempt: " + retryCount + " of " + MAX_RETRY_COUNT);
            return true;
        }
        return false;
    }

    public int getRetryCount() {
        return retryCount;
    }
}
