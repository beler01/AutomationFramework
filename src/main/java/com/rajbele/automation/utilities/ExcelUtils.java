package com.rajbele.automation.utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ExcelUtils class - Provides utilities for reading data from Excel files
 * Used for data-driven testing
 */
public class ExcelUtils {

    /**
     * Gets data from Excel file cell
     *
     * @param filePath Path to the Excel file
     * @param sheetName Sheet name in the Excel file
     * @param rowIndex Row index (0-based)
     * @param columnIndex Column index (0-based)
     * @return Cell value as String
     */
    public static String getCellData(String filePath, String sheetName, int rowIndex, int columnIndex) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.err.println("Sheet not found: " + sheetName);
                return null;
            }

            try {
                return sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets row count from Excel sheet
     *
     * @param filePath Path to the Excel file
     * @param sheetName Sheet name in the Excel file
     * @return Row count
     */
    public static int getRowCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.err.println("Sheet not found: " + sheetName);
                return 0;
            }

            return sheet.getPhysicalNumberOfRows();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Gets column count from Excel sheet
     *
     * @param filePath Path to the Excel file
     * @param sheetName Sheet name in the Excel file
     * @param rowIndex Row index (0-based)
     * @return Column count
     */
    public static int getColumnCount(String filePath, String sheetName, int rowIndex) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.err.println("Sheet not found: " + sheetName);
                return 0;
            }

            return sheet.getRow(rowIndex).getPhysicalNumberOfCells();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
