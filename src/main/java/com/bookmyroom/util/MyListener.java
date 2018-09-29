package com.bookmyroom.util;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MyListener implements ITestListener {
	 protected static WebDriver driver;
	 protected static ExtentReports reports;
	 public static ExtentTest test;
	
	 static org.apache.log4j.Logger log = Logger.getLogger(MyListener.class.getName());
	 
	 @Override
	 public void onTestStart(ITestResult result) {
	  log.debug("on test start");
	 // test = reports.startTest(BaseClass.testCaseName);
	 // test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
	 }
	 
	@Override
	 public void onTestSuccess(ITestResult result) {
		 log.debug("on test success");
		
	  test.log(LogStatus.PASS,  test.getTest().getName() + " is passed");
	 }
	 
	 @Override
	 public void onTestFailure(ITestResult result) {
		 Reusable util= new Reusable(driver);
		// Reusable util=new Reusable(driver);
		 log.debug("on test failure");
	  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " is failed");
	  //TakesScreenshot ts = (TakesScreenshot) driver;
	  //File src = ts.getScreenshotAs(OutputType.FILE);
	  try {
		  String location=util.takeSnapShot(result.getMethod().getMethodName());
	  // FileUtils.copyFile(src, new File("C:\\Users\\M1047105\\Desktop\\images\\" + result.getMethod().getMethodName() + ".png"));
	   String file = test.addScreenCapture(location);
	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " is failed", file);
	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " is failed", result.getThrowable().getMessage());
	  } catch (IOException e) {
		  log.debug(e);
	  }
	 }
	 
	@Override
	 public void onTestSkipped(ITestResult result) {
		 log.debug("on test skipped");
	  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + " is skipped");
	 }
	 
	 @Override
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 log.debug("on test sucess within percentage");
	 }
	 
	 @Override
	 public void onStart(ITestContext context) {
		 log.debug("on start");
		 
	// ApplicationTest.driver=driver; // Set the drivers path in environment variables to avoid code(System.setProperty())
	 
	// ApplicationTest.reportFile=reportFile;
	 //reports = new ExtentReports(reportFile);
	 }
	 
	 @Override
	 public void onFinish(ITestContext context) {
		 log.debug("on finish");
	  reports.endTest(test);
	  reports.flush();
	 }
	}