package com.rajbele.automation.constants;

/**
 * BrowserConstants - Contains browser-related constants
 */
public class BrowserConstants {

    // Browser types
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";
    public static final String SAFARI = "safari";

    // Browser modes
    public static final String HEADED_MODE = "headed";
    public static final String HEADLESS_MODE = "headless";

    // Common browser arguments
    public static final String DISABLE_GPU = "--disable-gpu";
    public static final String NO_SANDBOX = "--no-sandbox";
    public static final String DISABLE_DEV_SHM_USAGE = "--disable-dev-shm-usage";
    public static final String DISABLE_POPUP_BLOCKING = "--disable-popup-blocking";
    public static final String DISABLE_NOTIFICATIONS = "--disable-notifications";

    private BrowserConstants() {
        // Private constructor to prevent instantiation
    }
}
