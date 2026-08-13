package com.rajbele.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class - Reads properties from configuration files
 * Supports reading from config.properties file
 */
public class ConfigReader {

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
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + CONFIG_FILE_PATH);
            e.printStackTrace();
        }
    }

    /**
     * Gets property value by key
     *
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets property value by key with default value
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
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
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
     * @return Boolean value
     */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
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
     * Reloads properties from the configuration file
     */
    public static void reloadProperties() {
        loadProperties();
    }
}
