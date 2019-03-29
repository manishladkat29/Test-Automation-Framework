package dspPageObjects;

import java.util.HashMap;
import java.util.Map;

import utility.WebDr;

public class GoldAccountPage {
	public static void SetPageObjects(){
		Map<String, String> My_Page_Objects = new HashMap<String, String>();
		
		My_Page_Objects.put("lnk_OpenNow", "XPATH|//a[text()='Open now']");
		WebDr.page_Objects=My_Page_Objects;
	   
	}
}
