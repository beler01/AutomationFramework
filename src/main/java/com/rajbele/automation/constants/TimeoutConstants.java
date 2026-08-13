package com.rajbele.automation.constants;

/**
 * TimeoutConstants - Contains timeout constants for various wait operations
 */
public class TimeoutConstants {

    // Implicit wait timeouts (in seconds)
    public static final long IMPLICIT_WAIT = 10;

    // Explicit wait timeouts (in seconds)
    public static final long EXPLICIT_WAIT = 15;
    public static final long SHORT_WAIT = 5;
    public static final long LONG_WAIT = 30;

    // Page load timeout (in seconds)
    public static final long PAGE_LOAD_TIMEOUT = 30;

    // Thread sleep durations (in milliseconds)
    public static final long SMALL_SLEEP = 500;
    public static final long MEDIUM_SLEEP = 1000;
    public static final long LARGE_SLEEP = 2000;

    // Retry constants
    public static final int MAX_RETRY_COUNT = 3;
    public static final long RETRY_WAIT_TIME = 1000; // milliseconds

    private TimeoutConstants() {
        // Private constructor to prevent instantiation
    }
}
