package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class LoginPage {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	static org.apache.log4j.Logger log = Logger.getLogger(LoginPage.class.getName());
	
	
	public LoginPage(WebDriver driver){
       LoginPage.driver=driver;
	}
	
	public void login(String username,String password)
	{
		Reusable util=new Reusable(driver);
		String data[][];
		
		data = excelData.getData(Constants.ExcelPath, "Login");
		
		for(int i=0;i<data.length;i++)
        {
        	switch(data[i][Constants.keyword_col])
        	{
        	
        	case "navigateTo":
        		util.navigateTo(data[i][Constants.parameter_col]);
        		break;
        		
        	case "clickLogin":
        		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
        		
        	case "enterUsername":
        		util.enterText(data[i][Constants.pathType_col],data[i][Constants.path_col],username);
        	    break;
        		
        	case "enterPassword":
        		util.enterText(data[i][Constants.pathType_col],data[i][Constants.path_col],password);
        		break;
        	
        	case "clickSubmit":
        		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
        		break;
        		
        	/*case "verify":
        	util.verify(data[i][Constants.pathType_col],data[i][Constants.path_col], data[i][Constants.parameter_col]);
            break;*/
        		
        	default:log.debug("Invalid Keyword");
        	
        	}
        }
	
	}

}
