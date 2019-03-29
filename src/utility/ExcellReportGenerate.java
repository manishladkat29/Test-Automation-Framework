package utility;

import java.util.concurrent.TimeUnit;

    public class ExcellReportGenerate {
	
	public static String resultExcelPath = HtmlReporter.dirPath+ "\\result.xlsx";
	
	public static void generateSummaryReport() throws Exception{
		
	    ExcelUtils.setExcelFile(resultExcelPath,"Summary");
	    
	    ExcelUtils.setCellDataInteger(HtmlReporter.tc_count, 4, 0);
	    ExcelUtils.setCellDataInteger(HtmlReporter.tc_pass, 4, 1);
	    ExcelUtils.setCellDataInteger(HtmlReporter.tc_fail, 4, 2);
	    
	    ExcelUtils.setCellDataString(HtmlReporter.appName, 8, 0);
	    ExcelUtils. setCellDataString(HtmlReporter.appUrl, 8, 1);
	    ExcelUtils. setCellDataString(HtmlReporter.apEnv, 8, 4);
	    ExcelUtils. setCellDataString(HtmlReporter.appCycle, 8, 5);
	    
	    ExcelUtils. closeExcelFile(resultExcelPath);
	}
	
	public static void testWiseExcelReport(int row , int serialNum) throws Exception{
		ExcelUtils.setExcelFile(resultExcelPath,"Summary");
		ExcelUtils.setCellDataInteger(serialNum, row, 0);
		ExcelUtils. setCellDataString(HtmlReporter.testCase_ID, row, 1);
		ExcelUtils. setCellDataString(HtmlReporter.testStartTime, row, 2);
		ExcelUtils.setCellDataString(HtmlReporter.testEndTime, row, 3);
		ExcelUtils. setCellDataString(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(HtmlReporter.endTime - HtmlReporter.startTime)) +" sec", row, 4);
		ExcelUtils. setCellDataString(HtmlReporter.testResult, row, 5); 
		ExcelUtils. closeExcelFile(resultExcelPath);
	}
	
	public static void generateDetailedReport(int row , String decription, String expected, String actual , boolean status, int stepNo , String linkPath) throws Exception{
		ExcelUtils.setExcelFile(resultExcelPath,"Detailed");
		if(stepNo == 2){
			ExcelUtils.setCellDataString(HtmlReporter.testCase_ID, row, 0);
			ExcelUtils.setCellDataString(HtmlReporter.testCase_Name, row, 1);
		}
		ExcelUtils.setCellDataInteger(HtmlReporter.step_no-1, row, 2);
		ExcelUtils.setCellDataString(decription, row, 3);
		ExcelUtils.setCellDataString(expected, row, 4);
		ExcelUtils.setCellDataString(actual, row, 5);
		if(status == true){
			ExcelUtils.setCellDataString("Pass" , row, 6);
		}else{
			ExcelUtils.setCellDataString("Fail" , row, 6);
		}
        ExcelUtils.setCellDataHyperLink(row, 7 , stepNo , linkPath);
        ExcelUtils. closeExcelFile(resultExcelPath);
	}

	
		
}


