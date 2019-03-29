/*##############################################################################
'Class Name: Driver.java
'Description: 
'Prepared By: Minhaj Bakhsh
'Prepared On: 07/22/2015
'Updated By:
'Updated On:
'##############################################################################*/


package testCases;

import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.WebDriver;

//import com.experitest.client.Client;

import utility.CommonUtils;


import utility.Launcher;


public class Driver 
{
	public static WebDriver driver = null;
	public static Map<String, String> dictionary = new HashMap<String, String>();
	public static String userDir, file_TestData, appName;
	
	
	public static void AppInovker(String appName, String fn_name) throws Exception
	{
		
		switch (appName)
		{
			case "DSP":TestFlowDSP.executeTC(Driver.driver, fn_name); break;
			default:System.out.println("Application name not found"+ appName);
			break;
		}
	}
	
	public void mainDriver() throws Exception
	{	
		userDir=System.getProperty("user.dir");
		appName =CommonUtils.readConfig("ApplicationName");
		file_TestData= appName + "_MasterData.xlsx";
	
		Launcher.InvokeLauncher();
	}
	  
	public static void main(String ar[]) throws Exception
	{
		 Driver ob=new Driver();  
		 ob.mainDriver();
	}
	

}

