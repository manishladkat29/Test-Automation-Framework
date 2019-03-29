package dspPageObjects;

import java.util.HashMap;
import java.util.Map;

import utility.WebDr;

public class MainPage {
	public static void SetPageObjects(){
		Map<String, String> My_Page_Objects = new HashMap<String, String>();
		My_Page_Objects.put("lnk_GoldAccount", "XPATH|//h3[text()='UAT Links']/../p/a[text()='Gold Account']");
		
		WebDr.page_Objects=My_Page_Objects;
	   
	}
}
