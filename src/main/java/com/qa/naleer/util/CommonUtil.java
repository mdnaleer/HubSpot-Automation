package com.qa.naleer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtil {

	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH = "src/main/java/com/qa/naleer/data/TestData.xlsx";

	public static void takeScreenShot(WebDriver driver, String testCaseName) {
		TakesScreenshot scrShot = (TakesScreenshot) driver;

		File src = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("screenshots/Test - " + testCaseName + ".jpg"));
		} catch (IOException e) {
			System.out.println("IO Exception is handled");
		}

	}

	public static Object[][] getTestData(String sheetName){

		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception is handled. Please check for the file location");
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			System.out.println("Invalid format exception is handled. Please change to valid format");
		} catch (IOException e) {
			System.out.println("IO Exception is handled");
		}

		sheet = book.getSheet(sheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for(int i=0; i<sheet.getLastRowNum(); i++){
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++){
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}

		return data;

	}
}
