package com.rajbele.automation.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility to generate TestData.xlsx for data-driven testing
 * This class creates sample test data in Excel format
 */
public class GenerateTestData {

    public static void main(String[] args) {
        try {
            String testDataPath = "src/test/resources/testdata";
            File testDataDir = new File(testDataPath);
            if (!testDataDir.exists()) {
                testDataDir.mkdirs();
            }
            
            Workbook workbook = new XSSFWorkbook();
            
            // Create LoginData sheet
            createLoginDataSheet(workbook);
            
            // Create RegistrationData sheet
            createRegistrationDataSheet(workbook);
            
            // Create ProductData sheet
            createProductDataSheet(workbook);
            
            // Write to file
            String filePath = testDataPath + File.separator + "TestData.xlsx";
            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            
            System.out.println("TestData.xlsx created successfully at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error creating TestData.xlsx: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Creates LoginData sheet with sample login test data
     */
    private static void createLoginDataSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("LoginData");
        
        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"username", "password", "expected_result"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            formatHeaderCell(cell, workbook);
        }
        
        // Create sample data rows
        Object[][] data = {
            {"testuser1@example.com", "password123", "success"},
            {"testuser2@example.com", "wrongpass", "failure"},
            {"invalid_email", "password123", "failure"}
        };
        
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            Object[] rowData = (Object[]) data[i];
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData[j].toString());
            }
        }
        
        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    
    /**
     * Creates RegistrationData sheet with sample registration test data
     */
    private static void createRegistrationDataSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("RegistrationData");
        
        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"firstname", "lastname", "email", "password", "expected_result"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            formatHeaderCell(cell, workbook);
        }
        
        // Create sample data rows
        Object[][] data = {
            {"John", "Doe", "john.doe@example.com", "SecurePass123", "success"},
            {"Jane", "Smith", "jane.smith@example.com", "Pass456", "success"},
            {"Bob", "Johnson", "bob@invalid", "password", "failure"}
        };
        
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            Object[] rowData = (Object[]) data[i];
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData[j].toString());
            }
        }
        
        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    
    /**
     * Creates ProductData sheet with sample product test data
     */
    private static void createProductDataSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("ProductData");
        
        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"productname", "price", "category", "description"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            formatHeaderCell(cell, workbook);
        }
        
        // Create sample data rows
        Object[][] data = {
            {"Laptop", "999.99", "Electronics", "High performance laptop"},
            {"Mouse", "29.99", "Accessories", "Wireless mouse"},
            {"Keyboard", "79.99", "Accessories", "Mechanical keyboard"}
        };
        
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            Object[] rowData = (Object[]) data[i];
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                if (j == 1) { // Price column
                    cell.setCellValue(Double.parseDouble(rowData[j].toString()));
                } else {
                    cell.setCellValue(rowData[j].toString());
                }
            }
        }
        
        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    
    /**
     * Formats header cells with bold text and background color
     */
    private static void formatHeaderCell(Cell cell, Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(style);
    }
}
