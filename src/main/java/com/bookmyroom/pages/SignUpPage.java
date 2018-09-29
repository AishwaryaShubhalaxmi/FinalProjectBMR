package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Reusable;

public class SignUpPage {
	
	static WebDriver driver;
	GetExcelData excelData=new GetExcelData();
	
		
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());
	
	public SignUpPage(WebDriver driver){
		SignUpPage.driver=driver;
	}
	
	public void signUp(String name,String email,String userName,String password,String gender,String age,String phone,String expected) 
	{
		Reusable util=new Reusable(driver);
		String data[][];
		
			data = excelData.getData(Constants.ExcelPath, "SignUp");
			
			System.out.println("In signUpPage: "+Constants.ExcelPath);
		
		
		for(int i=0;i<data.length;i++)
        {
			System.out.println("----- Keyword: "+data[i][Constants.keyword_col]+" ------");
			switch(data[i][Constants.keyword_col])
	    	{
	    	
	    	case "navigateTo":
	    		System.out.println("Driver in SignUp: "+driver);
	    		System.out.println("----URL: "+data[i][Constants.parameter_col]);
	    		util.navigateTo(data[i][Constants.parameter_col]);
	    		util.implicitWait();
	    		break;
	    		
	    	case "click":
	    		util.click(data[i][Constants.pathType_col], data[i][Constants.path_col]);
	    		util.implicitWait();
	    		break;	    
	    		
	    	case "enterText":
	    		
	    		switch(data[i][Constants.parameter_col])
	    		{
	    		case"name":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], name);
	    			break;
	    		case"email":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], email);
	    			break;
	    		case"userName":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], userName);
	    			break;
	    		case"password":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], password);
	    			break;
	    		case"age":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], age);
	    			break;
	    		case"mobileNo":
	    			util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], phone);
	    			break;
	    		}
	    		//util.enterText(data[i][Constants.pathType_col], data[i][Constants.path_col], name);
	    		util.implicitWait();
	    		break;
	    		
	    	case "select":
	    		util.selectFromDropDownBox(data[i][Constants.pathType_col],data[i][Constants.path_col] ,gender);
	    		util.implicitWait();
	    	    break;
	    	    
	    	case "verifyTitle":
	    	util.verifyTitle(data[i][Constants.parameter_col]);
	    	util.implicitWait();
	    	break;
	    	//util.verify(data[i][Constants.pathType_col],data[i][Constants.path_col], data[i][Constants.parameter_col]);
	    	
	    	case "verifyPresence":
		    	util.verifyPresence(data[i][Constants.pathType_col], data[i][Constants.path_col],expected);
		    	//util.implicitWait();
		    	break;
	    	default:log.debug("Invalid Keyword");
	    	
	    	}
        }
		
	}

}
