package com.bookmyroom.testng;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bookmyroom.pages.Admin;
import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.MyListener;
import com.bookmyroom.util.ProgressBar;

@Listeners(com.bookmyroom.util.MyListener.class)
public class AdminTest extends MyListener {
	public static WebDriver driver;
	String reportFile;
	GetExcelData excelData=new GetExcelData();
	ProgressBar progress=new ProgressBar();
	static org.apache.log4j.Logger log = Logger.getLogger(AdminTest.class.getName());
	
	@DataProvider(name = "AdminData")	  
	 public Object[][] signUpData() throws IOException 
	{	
		String[][] data=excelData.getData(Constants.ExcelPath, "AdminData");
		progress.setVisible(true);
		progress.setStep(data.length);
		return data;
	}	
	@Test(dataProvider="AdminData")
	public void adminTest(String testCaseName, String email,String password) {
		log.info("------ Admin - "+testCaseName+" -------");
		test = reports.startTest(testCaseName);
		progress.setProgress();
		System.out.println("Driver in AppTest: "+driver);
		Admin ad= new Admin(driver);
		ad.adminFunctions(email, password);
	}

}
