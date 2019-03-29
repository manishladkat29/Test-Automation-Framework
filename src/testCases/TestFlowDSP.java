package testCases;


import org.openqa.selenium.WebDriver;

import dspResuableModule.TC01;



public class TestFlowDSP
{	
	public static void executeTC(WebDriver driver, String str_tc) throws Exception
	{
		switch(str_tc)
		{
			case "TC_01": TC_01(driver);break;//Verify the Standard Page header is displayed on the top of the screen for all the pages
			
			    
		}
	}
	//******************************************************************************************
	//Verify the Standard Page header is displayed on the top of the screen for all the pages
	public static void TC_01(WebDriver driver)throws Exception
	{
		TC01.Execute(driver);
	}
}