package utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class LoggerClass {
	
   public static String headerData[] ={"ApplicationName","Environment","ExecutionCycle","TestCaseId","TestCaseName","StartTime","EndTime","TotalTime","TestCaseResult","MachineName","TesterName"};
   public static String csv = "C:\\ExecutionLog\\Log.csv";
   
   public static boolean checkExecutionStatus(String execType){
		boolean status=false;
		if(execType.equals("1")){
			status=true;
	}	
		return status;
	}	
	
   public static boolean checkFrameworkVersion(String version){
	   boolean status=false;
		try {
			// Load MS access driver class
			String ServerShare = "\\\\satncrpfilprd\\projectdata\\BTS_Automation";
			String user="D_ABSA\\abnb516";
			String password = "Password1";
		    String command= "net use "+ ServerShare + " /user:" + user + " " + password; 
			Runtime.getRuntime().exec(command); 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String accessFileName = "\\\\satncrpfilprd\\projectdata\\BTS_Automation\\BTS_Automation_Artifacts\\Database\\BTSA_Main_DB";
			String url="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+accessFileName+".mdb;";
			Connection conn = DriverManager.getConnection(url,"","");
			System.out.println("Connection Succesfull");
			Statement stmt = conn.createStatement();

            stmt.execute("Select  Environment from ExecutionLog where ApplicationName = 'Version_ID'");

            ResultSet rs = stmt.getResultSet(); 

            if (rs != null)
             while ( rs.next() ){
                String frameworkVersion=rs.getString(1);
                System.out.println(frameworkVersion);
                if(frameworkVersion.equalsIgnoreCase(version)){
                	status=true;
                }
                }

                stmt.close();
                conn.close();
			} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

			}
		return status;
	}
   
   public static void writeCSV(String data) throws Exception{
	   CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
	   String [] dataToBeInserted = data.split("\\|");
	   writer.writeNext(dataToBeInserted);
	   writer.close();
   }
   
   public static void writeHeader() throws Exception{
	   CSVWriter writer = new CSVWriter(new FileWriter(csv));
	   writer.writeNext(headerData);
	   writer.close();
   }
   
   public static boolean checkHeaderExist() throws Exception{
	   boolean status=false;
	   CSVReader reader = new CSVReader(new FileReader(csv), ',' , '"' , 0);
	      String[] nextLine;
	      while ((nextLine = reader.readNext()) != null) {
	    	  if(nextLine != null){
	            //Verifying the read data here
	            System.out.println(Arrays.toString(nextLine));
	            String header[]=nextLine;
	            if(header.length == headerData.length){
	            	for(int i=0;i<header.length;i++){
		            	if(header[i].equals(headerData[i])){
		            		status=true;
		            	}else{
		            		status=false;
		            		break;
		            	}
		            }
	            }else{
	            	status=false;
            		break;
	            }
	         }
	    	 reader.close();
	         break;
	       }
	     
	      return status;
	     
   }
   
   public static void createLogInLocal(String data) throws Exception{
	   File file = new File("C:\\ExecutionLog");
		if (!file.exists()) {
			if (file.mkdir()) {
				writeHeader();
			} else {
				if(!checkHeaderExist()){
					writeHeader();		
				}
			}
		}
		writeCSV(data);
   }
  
   public static void fn_UpdateLogger(String data) throws Exception{
	   try {
		   String ServerShare = "\\\\satncrpfilprd\\projectdata\\BTS_Automation";
			 //String host = "Z";
			String user="D_ABSA\\abnb516";
			String password = "Password1";
			String command= "net use "+ ServerShare + " /user:" + user + " " + password; 
			Runtime.getRuntime().exec(command); 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String accessFileName = "\\\\satncrpfilprd\\projectdata\\BTS_Automation\\BTS_Automation_Artifacts\\Database\\BTSA_Main_DB";
			String url="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+accessFileName+".accdb;";
			Connection conn = DriverManager.getConnection(url,"","");
			System.out.println("Connection Succesfull");
			
			String LogData[] = data.split("\\|");
			
			String ApplicationName = LogData[0].trim();
			String Environmen = LogData[1].trim();
			String ExecutionCycle = LogData[2].trim();
			String TestCaseId = LogData[3].trim();
			String TestCaseName = LogData[4].trim();
			String StartTime = LogData[5].trim();
			String EndTime = LogData[6].trim();
			String TotalTime = LogData[7].trim();
			String TestCaseResult = LogData[8].trim();
			String MachineName = LogData[9].trim();
			String TesterName = LogData[10].trim();
			
			final String SQL_INSERT = "INSERT INTO Execution_Log (ApplicationName, Environment, ExecutionCycle, TestCaseId, TestCaseName, StartTime, EndTime, TotalTime, TestCaseResult, MachineName, TesterName)"
				    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			 PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
			 
			 statement.setString(1, ApplicationName);
			 statement.setString(2, Environmen);
			 statement.setString(3, ExecutionCycle);
			 statement.setString(4, TestCaseId);
			 statement.setString(5, TestCaseName);
			 statement.setString(6, StartTime);
			 statement.setString(7, EndTime);
			 statement.setString(8, TotalTime);
			 statement.setString(9, TestCaseResult);
			 statement.setString(10, MachineName);
			 statement.setString(11, TesterName);
			 statement.executeUpdate();
				System.out.println("data inserted in DB");
			 statement.close();
               conn.close();
			} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

			}
   }
}
