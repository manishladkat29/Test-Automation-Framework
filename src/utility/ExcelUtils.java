/*##############################################################################
'Class Name: ExcelUtils.java
'Description: 
'Prepared By: Minhaj Bakhsh
'Prepared On: 07/22/2015
'Updated By:
'Updated On:
'##############################################################################*/

package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testCases.Driver;

public class ExcelUtils 
{
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook = null; 
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path,String SheetName) throws Exception 
	{

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);		
			} 
			catch (Exception e)
			{
				throw (e);
			}

	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception
	{

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
			}
			catch (Exception e)
			{
			return"";
			}

	}

	//This method is to write in the Excel cell, Row num and Col num are the parameters

	/*public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	
	{

		try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) 
			{
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
			catch(Exception e)
			{
				throw (e);
			}
		}*/
	
	
	public static void closeExcelFile(String Path)throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelFile.close();
	}

	
	public static void setCellDataString(String Result,  int RowNum, int ColNum) throws Exception	
	{

		try{
			Row  = ExcelWSheet.getRow(RowNum);
			if(Row == null){
				ExcelWSheet.createRow(RowNum);
				Row  = ExcelWSheet.getRow(RowNum);
			}
			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) 
			{
				XSSFCellStyle my_style = ExcelWBook.createCellStyle();  
				cellBorderStyle(my_style,ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}

			
			// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream(ExcellReportGenerate.resultExcelPath);
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
			catch(Exception e)
			{
				throw (e);
			}
		}
	
	public static void setCellDataInteger(int Result,  int RowNum, int ColNum) throws Exception	
	{

		try{
			Row  = ExcelWSheet.getRow(RowNum);
			if(Row == null){
				ExcelWSheet.createRow(RowNum);
				Row  = ExcelWSheet.getRow(RowNum);
			}
			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) 
			{
				XSSFCellStyle my_style = ExcelWBook.createCellStyle();  
				cellBorderStyle(my_style,ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream(ExcellReportGenerate.resultExcelPath);
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
			catch(Exception e)
			{
				throw (e);
			}
		}
	
	public static void cellBorderStyle(XSSFCellStyle my_style,int ColNum) throws Exception{
		 
	 	my_style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	 	my_style.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        my_style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        my_style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        my_style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        Cell = Row.createCell(ColNum);
		 Cell.setCellStyle(my_style);
}

	public static void setCellDataHyperLink(int RowNum , int ColNum , int stepNo , String linkPath) throws Exception{
		XSSFCreationHelper helper= ExcelUtils.ExcelWBook.getCreationHelper();
        XSSFHyperlink url_link=helper.createHyperlink(Hyperlink.LINK_FILE);
        String path = HtmlReporter.dirPath.replace("\\", "/"); 
        String pth = linkPath.replace(".","");
        String screenshotPath = "file:///"+path+pth+"/Step-" + stepNo + ".png";
        if(screenshotPath.contains(" ")){        
        	screenshotPath = screenshotPath.replaceAll(" ", "%20");
        }
        url_link.setAddress(screenshotPath);
        url_link.setTooltip("Click Here To open the screenshot");
		try{
			Row  = ExcelWSheet.getRow(RowNum);
			if(Row == null){
				ExcelWSheet.createRow(RowNum);
				Row  = ExcelWSheet.getRow(RowNum);
			}
			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) 
			{
				XSSFCellStyle my_style = ExcelWBook.createCellStyle();  
				cellBorderStyle(my_style,ColNum);
				Cell.setCellValue("Click Here");         
				Cell.setHyperlink(url_link);
			} 
			else 
			{
				Cell.setCellValue("Click Here");         
				Cell.setHyperlink(url_link);
			}

			// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream(ExcellReportGenerate.resultExcelPath);
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
			catch(Exception e)
			{
				throw (e);
			}
		}
	
	
	
	
	public static void main(String ar[]) throws Exception
	{
		try {
		ExcelUtils.setExcelFile(Constant.Path_TestData + Driver.file_TestData,"Sheet1");
		}catch(Exception e)
		{
			e.getMessage();
		}
	}
}