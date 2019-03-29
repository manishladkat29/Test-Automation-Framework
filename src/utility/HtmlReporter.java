package utility;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testCases.Driver;

public class HtmlReporter 
{

	static File f,t;
	
	public static long startTime, endTime ;
	static BufferedWriter bw, bt;
	public static int step_no, tc_no, tc_count, tc_pass, tc_fail;
	public static boolean tc_result;
	public static String screenDirPath, testCase_Itr, testCase_ID, testCase_Name, testStartTime, testEndTime, testResult,executionType;

	public static long testTotalTime;
	static String appUrl, appName,apEnv,appCycle, htmlReport,captureScreen, dirPath , excelReport;
	static long totalStartTime=System.currentTimeMillis();
	static long totalEndTime=0;
	public static int serialNum = 1;
	public static int row = 1;
	public static void setUpReportFolder() throws Exception
	{
		//ExcellReportGenerate.generateDetailedReport();
		//ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Configuration");
 		ExcelUtils.setExcelFile(Constant.Path_TestData + Driver.file_TestData,"Configuration");
		htmlReport=ExcelUtils.getCellData(2,1);
		captureScreen=ExcelUtils.getCellData(3,1);
		//appName=ExcelUtils.getCellData(4,1);
		appName=Driver.appName;
		/*apEnv=ExcelUtils.getCellData(5,1);
		appCycle=ExcelUtils.getCellData(6,1);
		appUrl=ExcelUtils.getCellData(7,1);
		executionType=ExcelUtils.getCellData(8,1);
		excelReport = ExcelUtils.getCellData(9,1);*/
		apEnv=ExcelUtils.getCellData(4,1);
		appCycle=ExcelUtils.getCellData(5,1);
		appUrl=ExcelUtils.getCellData(6,1);
		executionType=ExcelUtils.getCellData(7,1);
		excelReport = ExcelUtils.getCellData(8,1);
		tc_no=0;
		dirPath=Driver.userDir + "\\TestResults\\" + appName + "-" + apEnv + "-" + appCycle + "-RESULT-" +  ((CommonUtils.getDate()).replace(':','-')).replaceAll("\\s","");
		File file = new File(dirPath);
		if (!file.exists()) 
		{
			if (file.mkdir()) 
			{
				//System.out.println("Directory is created!");
				String runTime=CommonUtils.getDate();
				String userName=System.getProperty("user.name");

				InetAddress addr;
				addr = InetAddress.getLocalHost();
				String hostName = addr.getHostName();
				
				//Copy the Excel template in the directory
				if(excelReport.equals("Yes")){
					File excelTemplate = new File(Constant.excelReportTemplate);
					File file1 = new File(dirPath + "\\result.xlsx");
					try {
					    FileUtils.copyFile(excelTemplate, file1);
					} catch (Exception e) {
					    e.printStackTrace();
					}
				}
				
				//Summary
				f = new File(dirPath + "\\TestResult_Summary.html");
				bw = new BufferedWriter(new FileWriter(f));
				tc_count=0;
				tc_pass=0;
				tc_fail=0;
				tc_result=true;
				bw.write("<HTML><HEAD><TITLE>Selenium Summary Report </TITLE></HEAD>");
				bw.write("<BODY ALIGN=BOTTOM ><BR><TR COLS=2><TD WIDTH=94% BGCOLOR=WHITE><FONT COLOR=#215967 SIZE=3 FACE=\"barclays sans\"><CENTER><B>Test Automation Execution Result - " + runTime + " on Machine " + hostName +" By " + userName.toUpperCase() + "</B></FONT></CENTER></TD></TR>");
				bw.write("<BR><TABLE BORDER=1 BGCOLOR=#595959><TR><TD COLSPAN=4 BGCOLOR=#92CDDC><FONT COLOR=WHITE SIZE=2 FACE=\"barclays sans\"><CENTER><B> Application Details </B></FONT></TD></TR>");
				//bw.write("<BR><TABLE BORDER=1 BGCOLOR=#595959><TR><TD COLSPAN=4 BGCOLOR=#489CDF><FONT COLOR=WHITE SIZE=2 FACE=\"barclays sans\"><CENTER><B> Application Details </B></FONT></TD></TR>");
				bw.write("<TR><TD WIDTH=150 BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Application Name </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=500  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Application URL </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=150  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Environment </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=150  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Execution Cycle </B></CENTER></FONT></TD></TR>");
				bw.write("<TR><TD WIDTH=150 BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + appName + "</CENTER></FONT></TD>");
				bw.write("<TD WIDTH=500  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + appUrl + "</CENTER></FONT></TD>");
				bw.write("<TD WIDTH=150  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + apEnv + "</CENTER></FONT></TD>");
				bw.write("<TD WIDTH=150  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + appCycle + "</CENTER></FONT></TD></TR></TABLE>");
				bw.write("<BR><TABLE BORDER=1 BGCOLOR=#595959><TR><TD COLSPAN=6 BGCOLOR=#92CDDC><FONT COLOR=WHITE SIZE=2 FACE=\"barclays sans\"><CENTER><B> Test Case Wise Report </B></CENTER></FONT></TD></TR>");
				//bw.write("<BR><TABLE BORDER=1 BGCOLOR=#595959><TR><TD COLSPAN=6 BGCOLOR=#489CDF><FONT COLOR=WHITE SIZE=2 FACE=\"barclays sans\"><CENTER><B> Test Case Wise Report </B></CENTER></FONT></TD></TR>");
				bw.write("<TR><TD WIDTH=100 BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Test Case Id  </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=500  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B>Test Case Name  </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=200  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Start Time  </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=200  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> End Time  </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=150  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Total Time  </B></CENTER></FONT></TD>");
				bw.write("<TD WIDTH=150  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Test Case Result  </B></CENTER></TD></TR>");
				
				
				//Details Report
				t = new File(dirPath + "\\TestResult_Detail.html");
				bt = new BufferedWriter(new FileWriter(t));
				
				
				bt.write("<HTML><HEAD><TITLE>Selenium Detailed Report </TITLE></HEAD>");
				bt.write("<BR><BR><BODY><TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
				bt.write("<TR><TD COLSPAN=6 BGCOLOR=#92CDDC><FONT COLOR=WHITE SIZE=3 FACE=\"barclays sans\"><CENTER><B> Detailed Report  </CENTER> </B></FONT></TD></TR>");
//				bt.write("<TR><TD COLSPAN=6 BGCOLOR=#489CDF><FONT COLOR=WHITE SIZE=3 FACE=\"barclays sans\"><CENTER><B> Detailed Report  </CENTER> </B></FONT></TD></TR>");
				bt.write("<TR COLS=6><TD BGCOLOR=#DAEEF3 WIDTH=75><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><CENTER><B> Step Number </B></CENTER></FONT></TD>");
				bt.write("<TD BGCOLOR=#DAEEF3 WIDTH=200><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><CENTER><B> Test Case Description </B></CENTER></FONT></TD>");
				bt.write("<TD BGCOLOR=#DAEEF3 WIDTH=300><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><CENTER><B> Expected Result </B></CENTER></FONT></TD>");
				bt.write("<TD BGCOLOR=#DAEEF3 WIDTH=300><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><CENTER><B> Actual Result </B></CENTER></FONT></TD>");
				bt.write("<TD BGCOLOR=#DAEEF3 WIDTH=75><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><CENTER><B> Status </B></CENTER></FONT></TD>");
				bt.write("<TD BGCOLOR=#DAEEF3 WIDTH=100><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><CENTER><B> Screen Shot </B></CENTER></FONT></TD></TR>");
		
			} 
			else 
			{
				//System.out.println("Failed to create directory!");
			}
		}
	}
	
	

	public static void testWiseReport() throws Exception
	{
		bt.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=75><FONT SIZE=2 FACE=\"barclays sans\">" + step_no + "</FONT></TD>");
		bt.write("<TD BGCOLOR=#EEEEEE WIDTH=200><FONT SIZE=2 FACE=\"barclays sans\">Test Case " + tc_no + "</FONT></TD>");
		bt.write("<TD BGCOLOR=#EEEEEE WIDTH=300><FONT SIZE=2 FACE=\"barclays sans\">Test Case " + tc_no + "- " + testCase_Name + " ENDS HERE</FONT></TD>");
		bt.write("<TD BGCOLOR=#EEEEEE WIDTH=300><FONT SIZE=2 FACE=\"barclays sans\"></A></FONT></TD>");
		bt.write("<TD WIDTH=75 BGCOLOR=#EEEEEE></TD><TD WIDTH=100 BGCOLOR=#EEEEEE></TD></TR>");

		tc_count++;
		
		bw.write("<TR><TD WIDTH=100 BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + testCase_ID + " </CENTER></FONT></TD>");
		bw.write("<TD WIDTH=500  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"> " + testCase_Name + "</FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + testStartTime + " </CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> " + testEndTime + "</CENTER></FONT></TD>");
		testTotalTime = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
		bw.write("<TD WIDTH=150  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER> "+testTotalTime+" Sec</CENTER></FONT></TD>");
		System.out.println("Start Time: "+ testStartTime);
		System.out.println("End Time: "+ testEndTime);
		if(tc_result==true)
		{
			tc_pass++;
			System.out.println("Status: PASSED");
			testResult="Pass";
			bw.write("<TD WIDTH=150  BGCOLOR=#FFFFFF><CENTER><FONT COLOR=GREEN SIZE=5 FACE='WINGDINGS 2'></FONT><B><A HREF=\"#" + testCase_ID + "\"><FONT COLOR=GREEN SIZE=2 FACE=\"barclays sans\"> Pass </B></CENTER></FONT></A></TD></TR>");
		}
		else
		{
			tc_fail++;
			System.out.println("Status: FAILED");
			testResult="Fail";
			bw.write("<TD WIDTH=150  BGCOLOR=#FFFFFF><CENTER><FONT COLOR=GREEN SIZE=5 FACE='WINGDINGS 2'></FONT><B><A HREF=\"#" + testCase_ID + "\"><FONT COLOR=RED SIZE=2 FACE=\"barclays sans\"> Fail </B></CENTER></FONT></A></TD></TR>");
		}
		tc_result=true;
		System.out.println("Test Case " + tc_no + "- " + testCase_Name + " END HERE - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		String strSharedLogData = appName + "|" + apEnv + "|" + appCycle + "|" + testCase_ID + "|" + testCase_Name + "|" + testStartTime + "|" + testEndTime + "|" + testTotalTime + "|" + testResult + "|" + InetAddress.getLocalHost().getHostName() + "|" + System.getProperty("user.name");
		if(LoggerClass.checkExecutionStatus(executionType) && LoggerClass.checkFrameworkVersion(Constant.frameworkVersion)){
		    System.out.println(strSharedLogData);
		    LoggerClass.fn_UpdateLogger(strSharedLogData);
		}else{ 
			LoggerClass.createLogInLocal(strSharedLogData);
		}
		
		if(excelReport.equals("Yes")){
			ExcellReportGenerate.testWiseExcelReport(Launcher.testWiseReport_Row , serialNum);
			serialNum=serialNum + 1;
		}
		
	}
	
	public static void endTestReport() throws Exception
	{
		totalEndTime =System.currentTimeMillis();
		bw.write("</TABLE><BR><TABLE BORDER=1 BGCOLOR=#595959><TR><TD COLSPAN=4 BGCOLOR=#92CDDC><FONT COLOR=WHITE SIZE=2 FACE=\"barclays sans\"><CENTER><B> Summary Report </B></CENTER></FONT></TD></TR>");
		//bw.write("</TABLE><BR><TABLE BORDER=1 BGCOLOR=#595959><TR><TD COLSPAN=4 BGCOLOR=#489CDF><FONT COLOR=WHITE SIZE=2 FACE=\"barclays sans\"><CENTER><B> Summary Report </B></CENTER></FONT></TD></TR>");
		bw.write("<TR><TD WIDTH=200 BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Total Test Cases </B></CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Total Test Cases Passed </B></CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Total Test Cases Failed </B></CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#DAEEF3><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> Total Test Cases Execution Time </B></CENTER></TD></TR>");
		bw.write("<TR><TD WIDTH=200 BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> " + tc_count + " </B></CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> " + tc_pass + " </B></CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> " + tc_fail + " </B></CENTER></FONT></TD>");
		bw.write("<TD WIDTH=200  BGCOLOR=#FFFFFF><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"><CENTER><B> " + TimeUnit.MILLISECONDS.toMinutes(totalEndTime-totalStartTime) + " Min</B></CENTER></TD></TR></TABLE></BODY></HTML>");

		bw.close();
		bt.close();
		File[] files = new File[2];
		files[0]=f;
		files[1]=t;
		File mergedFile = new File(dirPath + "\\TestReport.html");
		CommonUtils.mergeFiles(files, mergedFile);
		File htmlFile = new File(dirPath + "\\TestReport.html");
		Desktop.getDesktop().browse(htmlFile.toURI());
		
		if(excelReport.equals("Yes")){
		ExcellReportGenerate.generateSummaryReport();
		}
	}
	
	public static void WriteStep(String decription, String expected, String actual, boolean status ) throws Exception
	{
		
		String linkPath=null;
		if (step_no==0)
		{			
			screenDirPath=dirPath + "\\Screenshots\\" + testCase_Name + "-" + testCase_ID + "-" + testCase_Itr;
			File file = new File(screenDirPath);
			if (!file.exists()) 
			{
				if (file.mkdir()) 
				{
					
					//System.out.println("Screenshort dir created");
				}
			}
			
			
			bt.write("<TR COLS=2><TD BGCOLOR=#50B1C8 WIDTH=75><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><B> Test Case </B></FONT></TD>");
			bt.write("<TD ID=\""+ testCase_ID + "\" COLSPAN=6 BGCOLOR=#50B1C8 WIDTH=800><FONT COLOR=BLACK SIZE=2 FACE=\"barclays sans\"><B>" + testCase_ID + "- " +testCase_Name + " (Iteration-" + testCase_Itr + ") </B></FONT></TD> </TR>");
			bt.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=75><FONT SIZE=2 FACE=\"barclays sans\">1</FONT></TD>");
			bt.write("<TD BGCOLOR=#EEEEEE WIDTH=200><FONT SIZE=2 FACE=\"barclays sans\">Test Case " + tc_no + "</FONT></TD>");
			bt.write("<TD BGCOLOR=#EEEEEE WIDTH=300><FONT SIZE=2 FACE=\"barclays sans\">Test Case " + tc_no + "- " + testCase_Name + " STARTS HERE</FONT></TD>");
			bt.write("<TD BGCOLOR=#EEEEEE WIDTH=300><FONT SIZE=2 FACE=\"barclays sans\"></A></FONT></TD>");
			bt.write("<TD WIDTH=75 BGCOLOR=#EEEEEE></TD><TD WIDTH=100 BGCOLOR=#EEEEEE></TD></TR>");
			System.out.println("Step No: "+ (step_no + 1) + ">>Test Case " + tc_no + "- " + testCase_Name + " STARTS HERE -   ####################################################");
			
			step_no=2;
		}
		
		linkPath="./Screenshots/" + testCase_Name + "-" + testCase_ID + "-" + testCase_Itr;
		bt.write("<TR COLS=5><TD BGCOLOR=#EEEEEE WIDTH=75><FONT SIZE=2 FACE=\"barclays sans\">" + step_no + "</FONT></TD>");
		bt.write("<TD BGCOLOR=#EEEEEE WIDTH=200><FONT SIZE=2 FACE=\"barclays sans\">" + decription + "</FONT></TD>");
		bt.write("<TD BGCOLOR=#EEEEEE WIDTH=300><FONT SIZE=2 FACE=\"barclays sans\">" + expected + "</FONT></TD>");
		bt.write("<TD BGCOLOR=#EEEEEE WIDTH=300><FONT SIZE=2 FACE=\"barclays sans\">" + actual + "</A></FONT></TD>");
		
		System.out.print("Step No: "+step_no);
		System.out.print(">> DESCRIPTION: "+decription);
		System.out.print(">> EXPECTED: "+expected);
		System.out.print(">> ACTUAL: "+actual);
		if(status==true)
		{
			bt.write("<TD BGCOLOR=#EEEEEE WIDTH=75><FONT COLOR=GREEN  SIZE=5 FACE='WINGDINGS 2'></FONT><FONT COLOR=GREEN  SIZE=2 FACE=\"barclays sans\"><B> Pass </B></FONT></TD>");
			System.out.print(">> Status : PASS");		
		}
		else
		{
			tc_result=false;
			System.out.print(">> Status : FAIL");
			bt.write("<TD BGCOLOR=#EEEEEE WIDTH=75><FONT COLOR=GREEN  SIZE=5 FACE='WINGDINGS 2'></FONT><FONT COLOR=RED  SIZE=2 FACE=\"barclays sans\"><B> Fail </B></FONT></TD>");
		}
		System.out.println("");
		boolean capture=CaptureScreenShot(tc_result);
		if(capture==true){
		bt.write("<TD WIDTH=100 BGCOLOR=#EEEEEE><A HREF=\"" + linkPath + "/Step-" + step_no + ".png\" TARGET=_BLNAK><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"> Click Here </FONT></A></TD>");
		}else{
		bt.write("<TD WIDTH=100 BGCOLOR=#EEEEEE><FONT COLOR=#000000 SIZE=2 FACE=\"barclays sans\"> Alert Present On Screen </FONT></TD>");
		}
		
		//Part of ExcelReportgenerate
		if(excelReport.equals("Yes")){
		ExcellReportGenerate.generateDetailedReport(row, decription, expected, actual, status,step_no,linkPath);
		row = row + 1;
		}
		step_no++;
	}
	public static boolean CaptureScreenShot(boolean c_status) throws Exception
	{
		boolean caputre=false;
		boolean screenshot_present = false;
		if(captureScreen.equals("On Every Step"))
		{
			caputre=true;
		}
		else if(captureScreen.equals("Never"))
		{
			caputre=false;
		}
		else if(c_status==false)
		{
			caputre=true;
		}
		
		if(caputre==true)
		{
			try{
			File scrFile = ((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenDirPath + "\\Step-" + step_no + ".png"));
			screenshot_present= true;
			}catch(Exception e){
				System.out.println("alert Box open");
			}
		}
		return screenshot_present;
			
	}
}

