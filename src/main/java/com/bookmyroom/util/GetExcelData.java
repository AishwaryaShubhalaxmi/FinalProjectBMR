package com.bookmyroom.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelData {
	
	static org.apache.log4j.Logger log = Logger.getLogger(GetExcelData.class.getName());
	
public String[][] getData(String path,String sheetName) {
	
	System.out.println("Inside Get Excel:"+path+sheetName);
	
	String[][] data = null;
		
	try {
		InputStream excelFileToRead = new FileInputStream(path);
		
		XSSFWorkbook wb = new XSSFWorkbook(excelFileToRead);
		 XSSFSheet sheet = wb.getSheet(sheetName);        
        XSSFRow row; 
        int rows=sheet.getLastRowNum();
        int cols=sheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("Rows: "+rows+"Cols: "+cols);
        data = new String[rows][cols];
        for(int i=0;i<rows;i++)
        {
        	 row=sheet.getRow(i+1);
        	 for (int j = 0; j < row.getLastCellNum(); j++) {
                 data[i][j] = (row.getCell(j).toString());
             }
        }
       	
		 wb.close();
		 excelFileToRead.close();
        
	} catch (IOException e) {
		log.error("Error reading excel file",e);
	}
	
	System.out.println("Data: "+data);
	 return data;
}

}
