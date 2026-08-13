# Rajbele Selenium Hybrid Automation Framework

A production-style Selenium Hybrid Automation Framework built with Java 17, Selenium WebDriver 4.41.0, TestNG, and Maven.

## Framework Architecture

### Project Structure

```
rajbele-automation-framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/rajbele/automation/
│   │           ├── base/
│   │           │   ├── BaseTest.java          # Base test class with setup/teardown
│   │           │   ├── BasePage.java          # Base page object class
│   │           │   ├── DriverFactory.java     # WebDriver factory for multi-browser support
│   │           │   └── RetryAnalyzer.java     # Test retry analyzer
│   │           ├── config/
│   │           │   └── ConfigReader.java      # Configuration properties reader
│   │           ├── constants/
│   │           │   ├── TimeoutConstants.java  # Timeout constants
│   │           │   └── BrowserConstants.java  # Browser-related constants
│   │           ├── utilities/
│   │           │   ├── WaitUtils.java         # Explicit wait utilities
│   │           │   ├── CommonUtils.java       # Common utility methods
│   │           │   ├── ScreenshotUtils.java   # Screenshot capture utility
│   │           │   ├── ExtentReportsManager.java # HTML reporting
│   │           │   └── ExcelUtils.java        # Excel data reading
│   │           ├── listeners/
│   │           │   └── TestNGListener.java    # TestNG event listener
│   │           ├── pages/                     # Page Object Model classes (to be created)
│   │           └── testdata/                  # Test data files directory
│   └── test/
│       ├── java/
│       │   └── com/rajbele/automation/tests/  # Test classes (to be created)
│       └── resources/
│           ├── config.properties              # Application configuration
│           ├── testng.xml                     # TestNG suite configuration
│           ├── log4j2.xml                     # Log4j2 logging configuration
│           └── testdata/                      # Test data directory
│
├── pom.xml                                    # Maven project configuration
├── README.md                                  # This file
└── .gitignore                                 # Git ignore file
│           └── log4j2.xml
├── pom.xml
├── testng.xml
├── reports/              (Extent Reports generated here)
├── screenshots/          (Screenshots on failure)
├── logs/                 (Log files)
└── README.md
```

## Key Framework Features

### 1. **Configuration Management**
- `ConfigReader`: Centralized configuration from `config.properties`
- Properties include: browser, URL, timeouts, retry count, headless mode

### 2. **Driver Management**
- `DriverFactory`: Centralized WebDriver creation
- ThreadLocal support for thread-safe parallel execution
- Support for Chrome, Firefox, Edge browsers
- Automatic driver management via WebDriverManager

### 3. **Wait Utilities**
- `WaitUtils`: Explicit waits for:
  - Element visibility
  - Element clickability
  - Element presence
  - URL changes
  - Title changes
  - Element invisibility

### 4. **Common Utilities**
- `CommonUtils`: Reusable Selenium operations
  - Click, type, get text, get attributes
  - Form operations (submit)
  - Navigation operations
  - Element state checks

### 5. **Screenshot and Reporting**
- `ScreenshotUtils`: Capture screenshots on failure
- `ExtentReportsManager`: Extent Reports integration
- `TestNGListener`: Automatic screenshot attachment on test failure

### 6. **Logging**
- Log4j2 configuration for detailed logging
- Logs to console and file

### 7. **Retry Mechanism**
- `RetryAnalyzer`: Retry failed tests configurable times
- Prevents flaky test failures

### 8. **Page Object Model**
- `BasePage`: Parent class for all page objects
- Reusable locators using @FindBy annotations
- Separation of locators and test logic

## Test Coverage

### Smoke Tests (5 tests)
1. Verify home page loads
2. Verify products page navigation
3. Search products functionality
4. Add product to cart
5. Verify cart page opens

### Regression Tests (17 tests)

**Login & Signup:**
1. Login page displays
2. Signup section displays
3. Invalid login error message
4. Signup with existing email error
5. Login with valid credentials (placeholder)

**Products & Categories:**
6. Product details page
7. Product quantity selection
8. Women category filter
9. Men category filter
10. Search results relevance

**Cart Operations:**
11. Add multiple products
12. Cart displays products
13. Update product quantity
14. Remove product from cart

**Checkout & Contact:**
15. Checkout page navigation
16. Contact Us form submission
17. Address information during checkout

## Configuration (config.properties)

```properties
# Application Configuration
app.url=https://automationexercise.com/

# Browser Configuration
browser=chrome
headless=false

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=15
page.load.timeout=30

# Extent Reports
reports.path=./reports/
screenshots.path=./screenshots/

# Retry Configuration
retry.count=2

# Log Configuration
log.level=INFO
```

## Running Tests

### Prerequisites
- Java 17+
- Maven 3.6+
- Chrome/Firefox/Edge browser installed
- Internet connection for WebDriverManager

### Run All Tests
```bash
mvn clean test
```

### Run Smoke Tests Only
```bash
mvn clean test -Dgroups=smoke
```

### Run Regression Tests Only
```bash
mvn clean test -Dgroups=regression
```

### Run Tests in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Run Tests from Eclipse
1. Right-click on `testng.xml` → Run As → TestNG Suite
2. Or right-click on test class → Run As → TestNG Test

## Test Data & Credentials

### Login Credentials
- **Note**: Tests use placeholder credentials. Update in test classes as needed.
- Current tests use invalid credentials to test error handling

### Signup Email
- Tests generate unique test data or use existing test accounts
- Email validation is checked via existing email error message

## Generated Reports and Logs

### Extent Reports
- Location: `./reports/AutomationReport_<timestamp>.html`
- Contains: Pass/Fail status, screenshots, test duration, logs

### Screenshots
- Location: `./screenshots/`
- Captured automatically on test failure
- Named with timestamp: `<testname>_<timestamp>.png`

### Logs
- Console: Real-time logging during execution
- File: `./logs/automation.log`
- Level: INFO (configurable in log4j2.xml)

## Browser Support

### Configured Browsers
1. **Chrome** (default)
   - Headless mode supported: `headless=true` in config
   - Standard arguments: `--no-sandbox`, `--disable-dev-shm-usage`

2. **Firefox**
   - Headless mode: `-headless` argument

3. **Edge**
   - Similar configuration to Chrome

### Changing Browser
Update `config.properties`:
```properties
browser=firefox  # or edge
```

## Framework Best Practices Implemented

✓ Page Object Model (POM) for maintainability
✓ Separation of concerns (locators vs. test logic)
✓ ThreadLocal for thread-safe WebDriver management
✓ Explicit waits instead of Thread.sleep
✓ Centralized configuration
✓ Logging at every operation
✓ Screenshot capture on failure
✓ Retry mechanism for flaky tests
✓ TestNG listeners for event handling
✓ Extent Reports for detailed reporting

## Troubleshooting

### WebDriver Binary Issues
- Framework uses WebDriverManager (auto-downloads drivers)
- If issues persist, ensure Chrome/Firefox/Edge versions match

### Timeout Issues
- Increase `explicit.wait` in config.properties
- Check internet connectivity for WebDriverManager download

### Locator Issues
- Application may have changed UI elements
- Inspect application with browser DevTools (F12)
- Update locators in respective Page Object classes

### Test Failures
- Check `./reports/` for Extent Report with screenshots
- Check `./logs/automation.log` for detailed logs
- Retry failed tests (retry count: 2 by default)

## Advanced Features

### Parallel Execution
Update `testng.xml`:
```xml
<suite name="..." parallel="methods" thread-count="4">
```

### Custom Test Data
Use `ExcelUtils` to read from Excel files:
```java
ExcelUtils.loadExcel("path/to/testdata.xlsx", "SheetName");
Map<String, String> testData = ExcelUtils.getRowDataAsMap(0);
```

### Custom Reports
Modify `ExtentReportsManager` to customize report theme, title, etc.

## Support & Contribution

- Framework version: 1.0.0
- Last updated: 2026
- For issues or improvements, update page objects and utility classes

---

**Happy Testing!**
