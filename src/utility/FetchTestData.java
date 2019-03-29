
/*##############################################################################
'Class Name: FetchTestData.java
'Description: 
'Prepared By: Minhaj Bakhsh
'Prepared On: 07/22/2015
'Updated By:
'Updated On:
'##############################################################################*/
package utility;

import java.util.HashMap;
import java.util.Map;

import testCases.Driver;

//import java.util.Map;

public class FetchTestData 
{
	public static int tc_row=1;
	public static int curr_row=0;
	public static int data_row=0;
	public static int itr_size=1;
	public static String iteration_Flag = "Yes";
	
	public static Map<String, String> getCurrentTestData(String tc_id) throws Exception
	{	
		try {
		
		if(data_row==0)
		{
			
			//ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"TestData");
			ExcelUtils.setExcelFile(Constant.Path_TestData + Driver.file_TestData,"TestData");
			
			int j=1;
			int i=0;
			
			while(j==1)
			{
				
				if(ExcelUtils.getCellData(i,0).equals(tc_id))
				{
					//System.out.println(ExcelUtils.getCellData(i,0));
					curr_row=i;
					int s_size=0;
					int k=1;
					int z=i+1;
					
					while(k==1)
					{
						
						int x_size=ExcelUtils.getCellData(z,0).length() ;
						if(x_size != 0)
						{
							itr_size=s_size;
							j=0;
							k=0;
							//System.out.println(itr_size);
							
						}
						s_size++;
						z++;					
					}
				}
				i++;			
			}		
			data_row=curr_row+1;	
		}
		
		Map<String, String> dictionary = new HashMap<String, String>();
		//ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"TestData");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Driver.file_TestData,"TestData");
		
		int k=1;
		int i=2;
		while(k==1)
		{
			if(i==2){
				int iter_flag = ExcelUtils.getCellData(curr_row,i).length();
			    String interationFlagText = ExcelUtils.getCellData(curr_row,i) ;
				if(iter_flag != 0 && interationFlagText.equalsIgnoreCase("iterationFlag")){
					iteration_Flag = ExcelUtils.getCellData(data_row,i);
				}else{
					iteration_Flag = "Yes";
				}
				String key=ExcelUtils.getCellData(curr_row,i);
				String value="" + ExcelUtils.getCellData(data_row, i);
				dictionary.put(key, value);
				i++;
			}else{
				int x_size=ExcelUtils.getCellData(curr_row,i).length();
				if(x_size!=0)
				{
					String key=ExcelUtils.getCellData(curr_row,i);
					String value="" + ExcelUtils.getCellData(data_row, i);
					dictionary.put(key, value);
					i++;
				}
				else
				{
					k=0;
				}
				
			}
			
		}
		return dictionary;
		} catch (Exception e)
		{
			System.out.println("error while reading config - " + e );
			return null;
		}	
				
	}	
	
}
