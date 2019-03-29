package dspResuableModule;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dspPageObjects.ChequeAccountApplicationPage;
import dspPageObjects.GoldAccountPage;
import dspPageObjects.GoogleHomePage;
import dspPageObjects.MainPage;
import testCases.Driver;
import utility.HtmlReporter;
import utility.WebDr;


public class TC01 
{
	//********************************************************************************************************
	//Verify the Standard Page header is displayed on the top of the screen for all the pages
	public static void Execute(WebDriver driver) throws Exception
	{
		GoogleHomePage.SetPageObjects();
		//Thread.sleep(2000);
		//WebDr.setText("Search", WebDr.getValue("Search"), "The Search Value is entered");
		/*Driver.driver.findElement(By.xpath("//a[contains(,.'Entertainment')]")).click();
		if(Driver.driver.findElement(By.xpath("//input[@name='q']")).isEnabled())
		{
			System.out.println("Enabled");
		}*/
	}
		
}