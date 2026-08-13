package com.rajbele.automation.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.rajbele.automation.config.ConfigManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentReportsManager - Manages Extent Reports for HTML test reporting
 * Thread-safe implementation using ThreadLocal for parallel execution support
 * Provides methods to log test information and create reports
 */
public class ExtentReportsManager {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static final Object lock = new Object();

    /**
     * Initializes Extent Reports with HTML reporter
     * Thread-safe initialization using synchronized block
     */
    public static void initExtentReports() {
        if (extentReports == null) {
            synchronized (lock) {
                if (extentReports == null) {
                    String reportPath = getReportPath();
                    
                    // Create reports directory if it doesn't exist
                    File reportsDir = new File(ConfigManager.getReportsPath());
                    if (!reportsDir.exists()) {
                        reportsDir.mkdirs();
                    }

                    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
                    sparkReporter.config().setTheme(Theme.DARK);
                    sparkReporter.config().setDocumentTitle("Rajbele Automation Report");
                    sparkReporter.config().setReportName("Test Execution Report");

                    extentReports = new ExtentReports();
                    extentReports.attachReporter(sparkReporter);
                    extentReports.setSystemInfo("Application", "Rajbele");
                    extentReports.setSystemInfo("Environment", "QA");
                    extentReports.setSystemInfo("Browser", ConfigManager.getBrowser());
                    extentReports.setSystemInfo("Headless", String.valueOf(ConfigManager.isHeadless()));
                    extentReports.setSystemInfo("OS", System.getProperty("os.name"));
                    extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
                }
            }
        }
    }

    /**
     * Gets the report path with timestamp
     * 
     * @return Full path for the report file
     */
    private static String getReportPath() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        return ConfigManager.getReportsPath() + "AutomationReport_" + timestamp + ".html";
    }

    /**
     * Creates a new test in Extent Reports for the current thread
     *
     * @param testName Name of the test
     * @param testDescription Description of the test
     */
    public static void createTest(String testName, String testDescription) {
        initExtentReports();
        ExtentTest test = extentReports.createTest(testName, testDescription != null ? testDescription : "");
        extentTestThreadLocal.set(test);
    }

    /**
     * Logs info message to report for current thread
     *
     * @param message Message to log
     */
    public static void logInfo(String message) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.info(message);
        }
    }

    /**
     * Logs pass message to report for current thread
     *
     * @param message Message to log
     */
    public static void logPass(String message) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.pass(message);
        }
    }

    /**
     * Logs fail message to report for current thread
     *
     * @param message Message to log
     */
    public static void logFail(String message) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.fail(message);
        }
    }

    /**
     * Logs error message to report for current thread
     *
     * @param message Message to log
     */
    public static void logError(String message) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.fail(message);
        }
    }

    /**
     * Logs warning message to report for current thread
     *
     * @param message Message to log
     */
    public static void logWarning(String message) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.warning(message);
        }
    }

    /**
     * Attaches screenshot to report for current thread
     *
     * @param screenshotPath Path of the screenshot file
     */
    public static void attachScreenshot(String screenshotPath) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null && screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                System.err.println("Failed to attach screenshot to report: " + e.getMessage());
            }
        }
    }

    /**
     * Flushes and saves the report
     * Cleans up thread-local data
     */
    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
        // Clean up thread-local
        extentTestThreadLocal.remove();
    }

    /**
     * Gets the current ExtentTest instance for the thread
     *
     * @return ExtentTest instance for current thread
     */
    public static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }
}
