package dspPageObjects;

import java.util.HashMap;
import java.util.Map;

import utility.WebDr;

public class GoogleHomePage {
	public static void SetPageObjects(){
		Map<String, String> My_Page_Objects = new HashMap<String, String>();
		My_Page_Objects.put("Search", "XPATH|//input[@class='gLFyf gsfi']");
		
		WebDr.page_Objects=My_Page_Objects;
	}
}
