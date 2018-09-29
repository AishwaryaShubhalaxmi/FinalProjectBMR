package com.bookmyroom.testng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bookmyroom.pages.SignUpPage;
import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.MyListener;
import com.bookmyroom.util.ProgressBar;
import com.bookmyroom.util.SendMail;
import com.relevantcodes.extentreports.ExtentReports;


@Listeners(com.bookmyroom.util.MyListener.class)
public class SignUpTest extends MyListener {
	

	public static WebDriver driver;
	//Reusable util;
	String reportFile;
	GetExcelData excelData=new GetExcelData();
	ProgressBar progress=new ProgressBar();
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpTest.class.getName());
	
//SignUp
	@DataProvider(name = "SignUpSheetData")	  
	 public Object[][] signUpData() throws IOException 
	{	
		String[][] data=excelData.getData(Constants.ExcelPath, "SignUpData");
		progress.setVisible(true);
		progress.setStep(data.length);
		return data;
	}
	
	@Test(dataProvider="SignUpSheetData",groups = {"SignUp"})
	public void signUpTest(String testCaseName,String name,String email,String userName,String password,String gender,String age,String phone,String expected) throws IOException
	{
		log.info("------ SignUp - "+testCaseName+" -------");
		test = reports.startTest(testCaseName);
		progress.setProgress();
		System.out.println("Driver in AppTest: "+driver);
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.signUp(name, email,userName, password, gender, age, phone,expected);
       
	}	
	
	/* //Login
	@DataProvider(name = "LoginSheetData")	  
	 public Object[][] loginData() throws IOException 
	{		
		return excelData.getData(Constants.signUpExcelPath, "LoginData");
	}
	
	@Test(dataProvider="LoginSheetData",groups = {"Login"})
	public void loginTest(String testCaseName,String email,String password) throws IOException
	{
		log.info("------ Login - "+testCaseName+" -------");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.login(email, password);
	}	
	
	
	
	@DataProvider(name = "Data")	  
	 public String[] signUpData() throws IOException 
	{	
		String[] data= {"A","B","C"};
		return data;
	}
	
	@Test(dataProvider="Data")
	 public void TestDependency(String val)
	 {
		System.out.println("--**--"+val+"--**--");
		System.out.println("xxxxxxxxxxxxxx-- Before TestDependency --xxxxxxxxxxxxxxx");
	 }
	
	@Test(dependsOnMethods = { "TestDependency" })
	 public void TestDependencyTest()
	 {
		System.out.println("xxxxxxxxxxxxxx-- After TestDependency --xxxxxxxxxxxxxxx");
	 }
	*/
	@BeforeTest
	public void beforeTest() {	
		System.out.println("--------************---------");
		String driverPath=Constants.chromeDriverPath;
	    System.setProperty("webdriver.chrome.driver", driverPath);
	 driver= new ChromeDriver();
	 MyListener.driver=driver;
	 reportFile="D:\\ExtentReport\\"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html";
	 reports = new ExtentReports(reportFile);
		//String driverPath=Constants.chromeDriverPath;
	   // System.setProperty("webdriver.chrome.driver", driverPath);
	    //driver = new ChromeDriver();
	   // driver.get("https://bookmyroomangulardevdotnet.azurewebsites.net/Home");
	   // util=new Reusable(driver);
	}
	
	@AfterTest
	public void afterTest() {	
		progress.setVal();
		driver.close();
	}
	
	@AfterSuite
	public void afterSuite()
	{
		SendMail.sendMail(reportFile);
	}
	

}
