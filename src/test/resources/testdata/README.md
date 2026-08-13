# Test Data Setup

## TestData.xlsx Creation

The `GenerateTestData.java` utility creates the `TestData.xlsx` Excel file with sample test data for the framework's data-driven testing capabilities.

### How to Generate TestData.xlsx

#### Option 1: Using Maven
```bash
mvn test-compile
# The TestData.xlsx will be generated automatically during test compilation if you update pom.xml
```

#### Option 2: Manual Java Execution
1. Compile the project:
   ```bash
   mvn clean compile
   ```

2. Run the utility class from the testdata directory:
   ```bash
   cd src/test/resources/testdata
   java -cp "../../../../target/classes:../../../../target/test-classes" GenerateTestData
   ```

### TestData.xlsx Structure

The generated Excel file contains three sheets:

#### 1. LoginData Sheet
- **Columns**: username, password, expected_result
- **Sample Data**:
  - testuser1@example.com / password123 / success
  - testuser2@example.com / wrongpass / failure
  - invalid_email / password123 / failure

#### 2. RegistrationData Sheet
- **Columns**: firstname, lastname, email, password, expected_result
- **Sample Data**:
  - John / Doe / john.doe@example.com / SecurePass123 / success
  - Jane / Smith / jane.smith@example.com / Pass456 / success
  - Bob / Johnson / bob@invalid / password / failure

#### 3. ProductData Sheet
- **Columns**: productname, price, category, description
- **Sample Data**:
  - Laptop / 999.99 / Electronics / High performance laptop
  - Mouse / 29.99 / Accessories / Wireless mouse
  - Keyboard / 79.99 / Accessories / Mechanical keyboard

### Using TestData with DataProviders

Example usage in test class:
```java
@DataProvider(name = "loginData")
public Object[][] getLoginData() throws IOException {
    String filePath = "src/test/resources/testdata/TestData.xlsx";
    String sheetName = "LoginData";
    
    int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
    int colCount = ExcelUtils.getColumnCount(filePath, sheetName, 0);
    
    Object[][] data = new Object[rowCount - 1][colCount];
    
    for (int i = 1; i < rowCount; i++) {
        for (int j = 0; j < colCount; j++) {
            data[i - 1][j] = ExcelUtils.getCellData(filePath, sheetName, i, j);
        }
    }
    
    return data;
}

@Test(dataProvider = "loginData")
public void loginTest(String username, String password, String expectedResult) {
    // Test implementation
}
```

### Important Notes

- **No Real Credentials**: The sample data uses test email addresses and generic passwords. Never store real credentials in test data files.
- **File Location**: TestData.xlsx should be located in `src/test/resources/testdata/` directory.
- **Customization**: Modify the `GenerateTestData.java` file to add your own test data sheets and samples.
- **Excel Format**: Uses Apache POI to generate .xlsx format, compatible with modern Excel versions.
