/*##############################################################################
'Class Name: WebDr.java
'Description: 
'Prepared By: Minhaj Bakhsh
'Prepared On: 07/22/2015
'Date Modified: 9/29/2015
'Updated By:
'Updated On:
'##############################################################################*/


package utility;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
//import com.experitest.client.Client;








import testCases.Driver;

public class WebDr 
{
	public static Map<String, String> page_Objects = new HashMap<String, String>();
	public static WebElement element = null;
	public static String browser; 
	//public static Client client=null;
	
	//
	public static void openApplication( String URL) 
	{

		try
		{

		DesiredCapabilities oCap = null;
		if(Launcher.remoteFlag.equals("Yes"))
		{
			
			switch(browser)
			{
				case "Chrome" : 
					oCap=DesiredCapabilities.chrome();
					oCap.setBrowserName("chrome");break;
								
								
				case "Firefox": //Driver.driver = new FirefoxDriver();break;
	 				oCap=DesiredCapabilities.firefox();
	 				oCap.setBrowserName("firefox");break;
				case "IE": //Driver.driver = new FirefoxDriver();break;
	 				oCap=DesiredCapabilities.internetExplorer();
	 				oCap.setBrowserName("internet explorer");break;
				case "EDGE":
					oCap = DesiredCapabilities.edge();
					oCap.setBrowserName("EDGE");
					
				default:break;	
			}
			switch(Launcher.nodePlatform)
			{
				case "XP": oCap.setPlatform(Platform.XP);break;
				case "VISTA": oCap.setPlatform(Platform.VISTA);break;
				case "WIN8": oCap.setPlatform(Platform.WIN8);break;
				default:break;
			}
			
			Driver.driver=new RemoteWebDriver(new URL(Launcher.nodeURL + "/wd/hub"), oCap);
		}
		else
		{
						
			switch(browser)
			{
				case "Chrome" : 
					System.setProperty("webdriver.chrome.driver", Constant.Chrome_Driver);
					Driver.driver = new ChromeDriver();
					break;
				
				case "IE": 
					System.setProperty("webdriver.ie.driver", Constant.IE_Driver);
					oCap=DesiredCapabilities.internetExplorer();
					oCap.setCapability("ignoreZoomSetting", true);
					oCap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
					Driver.driver = new InternetExplorerDriver(oCap);
					break;			
														
				case "Firefox": 
					Driver.driver = new FirefoxDriver();
					break;	
					
				case "EDGE":
					System.setProperty("webdriver.edge.driver", Constant.Edge_Driver);
					oCap=DesiredCapabilities.edge();
					oCap.setCapability("ignoreZoomSetting", true);
					oCap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
					Driver.driver = new EdgeDriver(oCap);
					break;
				
				default:break;	
				
				/*case "Mobile": 
					client= new Client(Driver.host,Driver.port,true);
					client.setProjectBaseDirectory(Driver.mobileAppFilePath);
					client.setReporter("xml", "reports", "Untitled");
					client.setDevice(Driver.deviceName);break;*/
			}
		}
		if (browser.equals("Mobile")) {
			/*client.launch(URL, Driver.instrument, true);//client.launch(activityURL, instrument, stopIfRunning);
			Thread.sleep(3000);
			client.elementSendText("", "", 0, ""); //client.elementSendText(zone, element, index, text);
			client.closeDevice();
			client.exit();*/
		}else{
			
			
			Driver.driver.manage().window().maximize();
			Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Driver.driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			Driver.driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
			Driver.driver.get(URL);	
		}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Failed to create driver object -" + " Method openApplication in WebDr");
			System.exit(1);
		}
	
	
	}
	//#######################################################################################
	public static void speakText(String textToSpeak)
	{
		try 
		{
	      Runtime.getRuntime().exec("wscript "+ Constant.speaker+" \""+ textToSpeak + "\"");
	      Thread.sleep(2000);
		}
		catch( Exception e ) { }
		
	}	
	//#######################################################################################
	public static String getValue(String objectName)
	{
		try
		{
			return Driver.dictionary.get(objectName);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	//#######################################################################################
	public static WebElement getElement(String str)
	{
		//System.out.println("WebDr.java- getElement Invoked");
		try
		{
			String desc=page_Objects.get(str);
			String [] a= desc.split("\\|");
			switch(a[0])
			{
				case "ID":  return Driver.driver.findElement(By.id(a[1]));
				case "CLASSNAME":  return Driver.driver.findElement(By.className(a[1]));
				case "LINKTEXT":  return Driver.driver.findElement(By.linkText(a[1]));
				case "NAME":  return Driver.driver.findElement(By.name(a[1]));
				case "XPATH":  return Driver.driver.findElement(By.xpath(a[1]));
				default: System.out.println("Function getElement cannot return object for " + str);break;
			}
		}
		catch(Exception e){System.out.println("Exeption in WebDr.getElement - "+e);return null;}
		return null;		
	}
	//#######################################################################################
	public static List<WebElement> getElements(String str)
	{
		//System.out.println("WebDr.java- getElement Invoked");
		try
		{
			String desc=page_Objects.get(str);
			String [] a= desc.split("\\|");
			switch(a[0])
			{
				case "ID":  return Driver.driver.findElements(By.id(a[1]));
				case "XPATH":  return Driver.driver.findElements(By.xpath(a[1]));
				default: break;
			}
		}
		catch(Exception e){System.out.println("Exeption in WebDr.getElements - "+e);return null;}
		return null;		
	}
	//#######################################################################################
	public static Select getWebList(String str)
	{
		Select webList=new Select(getElement(str));	
		return webList;
	}
	//#######################################################################################
	
	//******************************************************************************************
	public static boolean exists(String elementName, boolean expected, String  description) throws Exception
	{
		//System.out.println("WebDr.java- exists Invoked");
		boolean state=false;
		try
		{
			WebElement elmn=getElement(elementName);
			
			
			if(elmn.isDisplayed() && expected==true)
			{
				HtmlReporter.WriteStep(description, "Exists", "Exists" , true );
				state= true;
			}
			else if(elmn.isDisplayed() && expected==false)
			{
				HtmlReporter.WriteStep(description, "Should not Exists", "Exists" , false );
				state= false;
			}
			else if(!elmn.isDisplayed() && expected==true)
			{
				HtmlReporter.WriteStep(description, "Exists", "Does not exists" , false );
				state= false;
			}
			else if(!elmn.isDisplayed() && expected==false)
			{
				HtmlReporter.WriteStep(description, "Does not exists", "Does not exists" , true );
				state=true;
			}
		}
		catch(Exception e)
		{
			if(expected==true)
			{
				HtmlReporter.WriteStep(description, "Exists", "Does not exists" , false );
				state= false;
			}
			else if(expected==false)
			{
				HtmlReporter.WriteStep(description, "Does not exists", "Does not exists" , true );
				state=false;
			}
			
		}
		return state;
		
	}
	//******************************************************************************************
	public static boolean enabled(String elementName, boolean expected, String  description) throws Exception
	{
		//System.out.println("WebDr.java- exists Invoked");
		WebElement elmn=getElement(elementName);
		boolean state=false;
		try
		{
			if(elmn.isEnabled() && expected==true)
			{
				HtmlReporter.WriteStep(description, "Exists", "Exists" , true );
				state= true;
			}
			else if(elmn.isEnabled() && expected==false)
			{
				HtmlReporter.WriteStep(description, "Should not Exists", "Exists" , false );
				state= false;
			}
			else if(!elmn.isEnabled() && expected==true)
			{
				HtmlReporter.WriteStep(description, "Exists", "Does not exists" , false );
				state= false;
			}
			else if(!elmn.isEnabled() && expected==false)
			{
				HtmlReporter.WriteStep(description, "Does not exists", "Does not exists" , true );
				state=true;
			}
			
		}
		catch(Exception e){System.out.println("Exception in WebDr.enabled - "+e);return false;}
		return state;
		
	}
	//******************************************************************************************
		public static boolean displayed(String elementName, boolean expected, String  description) throws Exception
		{
			//System.out.println("WebDr.java- exists Invoked");
			WebElement elmn=getElement(elementName);
			boolean state=false;
			
			try
			{
					
				if(elmn.isDisplayed() && expected==true)
				{
					HtmlReporter.WriteStep(description, "Exists", "Exists" , true );
					state= true;
				}
				else if(elmn.isDisplayed() && expected==false)
				{
					HtmlReporter.WriteStep(description, "Should not Exists", "Exists" , false );
					state= false;
				}
				else if(!elmn.isDisplayed() && expected==true)
				{
					HtmlReporter.WriteStep(description, "Exists", "Does not exists" , false );
					state= false;
				}
				else if(!elmn.isDisplayed() && expected==false)
				{
					HtmlReporter.WriteStep(description, "Does not exists", "Does not exists" , true );
					state=true;
				}
			}
			catch(Exception e){System.out.println("Exeption in WebDr.displayed - "+e);}
			return state;
			
		}
	//******************************************************************************************
	public static void click(String elementName, String description) throws Exception
	{
		//System.out.println("WebDr.java- exists Invoked");
		try
		{
			WebElement elmn=getElement(elementName);
			if(elmn.isDisplayed() && elmn.isEnabled())
			{			
				elmn.click();
				HtmlReporter.WriteStep(description, "Click", "Clicked" , true );
			}
			else
			{
				
				HtmlReporter.WriteStep("Object not visible - " + description, "Click", "Not Clicked" , false );
			}
		}
		catch(Exception e){System.out.println("Exeption in WebDr.click - "+e);}
	}
	//******************************************************************************************
	public static void dblClick(String elementName, String description) throws Exception
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			
			
			//System.out.println("WebDr.java- exists Invoked");
			WebElement elmn=getElement(elementName);
			if(elmn.isDisplayed() && elmn.isEnabled())
			{			
				action.doubleClick(elmn).perform();
				HtmlReporter.WriteStep(description, "Double Click", "Clicked" , true );
			}
			else
			{
				HtmlReporter.WriteStep("Object not visible - " + description, "Double Click", "Not Clicked" , false );
			}
		}
		catch(Exception e){System.out.println("Exeption in WebDr.dblClick - "+e);}
	}
	//******************************************************************************************
	public static void rightClick(String elementName, String description) throws Exception
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			
			//System.out.println("WebDr.java- exists Invoked");
			WebElement elmn=getElement(elementName);
			if(elmn.isDisplayed() && elmn.isEnabled())
			{			
				action.contextClick(elmn).perform();
				HtmlReporter.WriteStep(description, "Right Click", "Clicked" , true );
			}
			else
			{
				HtmlReporter.WriteStep("Object not visible - " + description, "Right Click", "Not Clicked" , false );
			}
		}
		catch(Exception e){System.out.println("Exeption in WebDr.rightClick - "+e);}
	}
	//******************************************************************************************
		public static void mouseOver(String elementName, String description) throws Exception
		{
			try
			{
				Actions action = new Actions(Driver.driver);
				
				
				//System.out.println("WebDr.java- exists Invoked");
				WebElement elmn=getElement(elementName);
				if(elmn.isDisplayed() && elmn.isEnabled())
				{			
					action.moveToElement(elmn).perform();
					HtmlReporter.WriteStep(description, "Mouse Over", "Mouse Moved" , true );
				}
				else
				{
					HtmlReporter.WriteStep("Object not visible - " + description, "Mouse Over", "Mouse not moved" , false );
				}
			}
			catch(Exception e){System.out.println("Exeption in WebDr.mouseOver - "+e);}
		}
	//******************************************************************************************
	
	//******************************************************************************************
	public static void setText(String elementName, String textToSet, String description) throws Exception
	{
		//System.out.println("WebDr.java- setText Invoked");
		try
		{
			if(textToSet!=null)
			{
				WebElement elmn=getElement(elementName);
				if(elmn.isDisplayed())
				{
					elmn.clear();
					elmn.sendKeys(textToSet);
					HtmlReporter.WriteStep(description, "Enter Text", "Entered - " + textToSet, true );
				}
				else
				{
					HtmlReporter.WriteStep("Object not visible - " + description, "Enter Text", "Not Entered - " + textToSet , false );
				}
			}
		}
		catch(Exception e){System.out.println("Exeption in WebDr.setText - "+e);}
		
	}
	
	//******************************************************************************************
	
	public static void verifyText(String elementName, boolean matchSubString, String expectedText, String description) throws Exception
	{
		try
		{
			WebElement elmn=getElement(elementName);
			if(elmn.isDisplayed())
			{
				String actualText=elmn.getText();
				if (matchSubString==true)
				{
					if(actualText.contains(expectedText))
					{
						HtmlReporter.WriteStep(description, "Verify Text", "Verified - " + expectedText, true );
					}
					else
					{
						HtmlReporter.WriteStep(description, "Verify Text -" + expectedText, "Verification failed - " + actualText, false );
					}
				}
				else
				{			
					if(actualText.equals(expectedText))
					{
						HtmlReporter.WriteStep(description, "Verify Text", "Verified - " + expectedText, true );
					}
					else
					{
						HtmlReporter.WriteStep(description, "Verify Text -" + expectedText, "Verification failed - " + actualText, false );
					}
				}
			}
			else
			{
				HtmlReporter.WriteStep("Object not visible - " + description, "Verify Text -" + expectedText, "Not Verified", false );
			}
		}
		catch(Exception e){
			System.out.println("Exeption in WebDr.verifyText - "+e);
			}
	}
	
	//******************************************************************************************
	public static void verifyAttribute(String elementName, boolean matchSubString, String attribute, String expectedText, String description) throws Exception
	{
		try
		{
			WebElement elmn=getElement(elementName);
			if(elmn.isDisplayed())
			{
				String actualText=elmn.getAttribute(attribute);
				if (matchSubString==true)
				{
					if(actualText.contains(expectedText))
					{
						HtmlReporter.WriteStep(description, "Verify Attribute", "Verified - " + expectedText, true );
					}
					else
					{
						HtmlReporter.WriteStep(description, "Verify Attribute -" + expectedText, "Verification failed - " + actualText, false );
					}
				}
				else
				{			
					if(actualText.equals(expectedText))
					{
						HtmlReporter.WriteStep(description, "Verify Attribute", "Verified - " + expectedText, true );
					}
					else
					{
						HtmlReporter.WriteStep(description, "Verify Attribute -" + expectedText, "Verification failed - " + actualText, false );
					}
				}
			}
			else
			{
				HtmlReporter.WriteStep("Object not visible - " + description, "Verify Attribute -" + expectedText, "Not Verified", false );
			}
		}
		catch(Exception e){
			System.out.println("Exeption in WebDr.verifyAttribute - "+e);
			}
	}
	
	//******************************************************************************************

	public static void alertCheck(String expectedText,String command) throws Exception{
		Alert alert = Driver.driver.switchTo().alert();
		String alertText=alert.getText();
		System.out.println(alertText);
		if(command.equalsIgnoreCase("accept")){
			alert.accept();
		}else{
			alert.dismiss();
		}
		Thread.sleep(3000);
		if(alertText.equals(expectedText)){
		HtmlReporter.WriteStep("Checking Whether alert box is displaying after clicking on link", expectedText, alertText, true);
		}else{
		HtmlReporter.WriteStep("Checking Whether alert box is displaying after clicking on link", expectedText, alertText, false);
		}
		Thread.sleep(4000);
	}
	

}



