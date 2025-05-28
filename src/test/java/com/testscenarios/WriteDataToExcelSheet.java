package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteDataToExcelSheet {
	@Test
	public void f() throws Exception {
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\TestDataNew.xlsx");
		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet("Testabc");
		// row
		Row r = s.getRow(1);
		// Column
		Cell abc = r.createCell(2);
		abc.setCellValue("Narayana");
		
		FileOutputStream fo = new FileOutputStream(".\\src\\test\\resources\\testdata\\TestDataNew.xlsx");
		w.write(fo);
		w.close();
	}
}
