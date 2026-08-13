package com.rajbele.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager class - Centralized configuration management
 * Reads properties from config.properties file
 * Provides type-safe access to configuration values with defaults
 */
public class ConfigManager {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Loads properties from the configuration file
     */
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            System.out.println("Configuration loaded from: " + CONFIG_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + CONFIG_FILE_PATH);
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH, e);
        }
    }

    /**
     * Gets string property value by key
     *
     * @param key Property key
     * @return Property value or null if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets string property value by key with default value
     *
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Gets integer property value
     *
     * @param key Property key
     * @return Integer value
     * @throws NumberFormatException if value cannot be parsed as integer
     */
    public static int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return Integer.parseInt(value);
    }

    /**
     * Gets integer property value with default
     *
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Integer value or default value
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return (value != null) ? Integer.parseInt(value) : defaultValue;
    }

    /**
     * Gets boolean property value
     *
     * @param key Property key
     * @return Boolean value (case-insensitive)
     * @throws RuntimeException if property not found
     */
    public static boolean getBooleanProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return Boolean.parseBoolean(value);
    }

    /**
     * Gets boolean property value with default
     *
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Boolean value or default value
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * Gets long property value
     *
     * @param key Property key
     * @return Long value
     * @throws NumberFormatException if value cannot be parsed as long
     */
    public static long getLongProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return Long.parseLong(value);
    }

    /**
     * Gets long property value with default
     *
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Long value or default value
     */
    public static long getLongProperty(String key, long defaultValue) {
        String value = properties.getProperty(key);
        return (value != null) ? Long.parseLong(value) : defaultValue;
    }

    /**
     * Gets double property value
     *
     * @param key Property key
     * @return Double value
     * @throws NumberFormatException if value cannot be parsed as double
     */
    public static double getDoubleProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return Double.parseDouble(value);
    }

    /**
     * Gets double property value with default
     *
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Double value or default value
     */
    public static double getDoubleProperty(String key, double defaultValue) {
        String value = properties.getProperty(key);
        return (value != null) ? Double.parseDouble(value) : defaultValue;
    }

    /**
     * Checks if a property key exists
     *
     * @param key Property key
     * @return true if property exists, false otherwise
     */
    public static boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    /**
     * Reloads properties from the configuration file
     */
    public static void reloadProperties() {
        loadProperties();
    }

    /**
     * Gets all properties (useful for debugging)
     *
     * @return Properties object containing all configuration
     */
    public static Properties getAllProperties() {
        return new Properties(properties);
    }

    /**
     * Application-specific configuration convenience methods
     */

    /**
     * Gets browser type from configuration
     *
     * @return Browser type (default: chrome)
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Gets application URL from configuration
     *
     * @return Application base URL
     */
    public static String getApplicationUrl() {
        return getProperty("url", "https://automationexercise.com/");
    }

    /**
     * Gets implicit wait timeout in seconds
     *
     * @return Implicit wait timeout (default: 0)
     */
    public static int getImplicitWait() {
        return getIntProperty("implicitWait", 0);
    }

    /**
     * Gets explicit wait timeout in seconds
     *
     * @return Explicit wait timeout (default: 15)
     */
    public static int getExplicitWait() {
        return getIntProperty("explicitWait", 15);
    }

    /**
     * Checks if headless mode is enabled
     *
     * @return true if headless, false otherwise (default: false)
     */
    public static boolean isHeadless() {
        return getBooleanProperty("headless", false);
    }

    /**
     * Gets retry count for RetryAnalyzer
     *
     * @return Number of retries (default: 1)
     */
    public static int getRetryCount() {
        return getIntProperty("retryCount", 1);
    }

    /**
     * Gets reports directory path
     *
     * @return Reports directory path (default: ./reports/)
     */
    public static String getReportsPath() {
        return getProperty("reports.path", "./reports/");
    }

    /**
     * Gets screenshots directory path
     *
     * @return Screenshots directory path (default: ./screenshots/)
     */
    public static String getScreenshotsPath() {
        return getProperty("screenshots.path", "./screenshots/");
    }

    /**
     * Gets log level configuration
     *
     * @return Log level (default: INFO)
     */
    public static String getLogLevel() {
        return getProperty("log.level", "INFO");
    }
}
