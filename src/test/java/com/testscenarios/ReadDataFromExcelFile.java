package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadDataFromExcelFile {
  @Test
  public void f() throws Exception {
	  //Read the data from Excel
	  
	  FileInputStream fi  = new FileInputStream(".\\src\\test\\resources\\testdata\\TestDataNew.xlsx");
	  Workbook w = new XSSFWorkbook(fi);
	  Sheet s = w.getSheet("Testabc");
	  //row
	  Row r = s.getRow(1);
	  //Column
	  Cell emailAddress = r.getCell(0);
	  Cell pswd = r.getCell(5);
	  
	  System.out.println(emailAddress.getStringCellValue());
	  
	  System.out.println(pswd.getStringCellValue());
	  
	  
	  
  }
}
