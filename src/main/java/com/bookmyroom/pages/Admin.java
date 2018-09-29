package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class Admin {
	static WebDriver driver;
	GetExcelData excelData= new GetExcelData();
	
	static org.apache.log4j.Logger log= Logger.getLogger(Admin.class.getName());
	public Admin(WebDriver driver) {
		Admin.driver=driver;
	}
	
	public void adminFunctions(String email, String password) {
		Reusable util= new Reusable(driver);
		String[][] data;
		data=excelData.getData(Constants.ExcelPath,"Admin");
		System.out.println("In Admin Page: "+Constants.ExcelPath);
		
		for(int i=0;i<data.length;i++) {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			
			switch(data[i][Constants.keyword_col]) {
			case "navigateTo":
				util.navigateTo(data[i][Constants.parameter_col]);
				util.implicitWait();
				
			case "click":
				util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
				util.implicitWait();
				
			case "enterText":
				switch(data[i][Constants.parameter_col]) {
				case "email":
					util.enterText(data[i][Constants.pathType_col],data[i][Constants.path_col], email);
				case "password":
					util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], password);
				}
				
			case "clickOnGetAccess":
				util.click(data[i][Constants.pathType_col],data[i][Constants.path_col]);
				
			case "clickOnOK":
				util.click(data[i][Constants.path_col],data[i][Constants.path_col]);
				
			}
			
		}
		
		
		
	}

}
