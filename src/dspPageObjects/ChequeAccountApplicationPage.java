package dspPageObjects;

import java.util.HashMap;
import java.util.Map;

import utility.WebDr;

public class ChequeAccountApplicationPage {
	public static void SetPageObjects(){
		Map<String, String> My_Page_Objects = new HashMap<String, String>();
		My_Page_Objects.put("chk_Acknowledge", "XPATH|//md-checkbox[@ng-model='started.agree']");
		My_Page_Objects.put("rad_is_insolvent_no", "XPATH|//md-radio-button[@name='is_insolvent' and @aria-label='no']");
		My_Page_Objects.put("rad_debt_counselling_no", "XPATH|//md-radio-button[@name='debt_counselling' and @aria-label='no']");
		
		My_Page_Objects.put("btn_Next1","XPATH|//button[@class='continue-button active']");
		
		//Page 2
		
		//My_Page_Objects.put("chk_GoldValueBundle", "XPATH|//md-checkbox[@aria-label='Gold Value Bundle']");
		//My_Page_Objects.put("chk_GoldValueBundle2", "XPATH|//span[text()='Choose the way you would like to be billed for your account']");
		
		My_Page_Objects.put("chk_GoldValueBundle", "XPATH|//md-checkbox[@aria-label='Gold Value Bundle']/div[1]");
		
		My_Page_Objects.put("chk_GoldValueBundle", "XPATH|//md-checkbox[@aria-label='Gold Value Bundle']");
		
		
		////span[text()='Select your account features']
		
		
		
		My_Page_Objects.put("btn_Next2","ID|submitPricingForm");
		
		My_Page_Objects.put("btn_Next3","ID|submitFeaturesForm");
		
		//Details Sections
		My_Page_Objects.put("rad_Title_Mr", "XPATH|//md-radio-button[@aria-label='Mr']");
		My_Page_Objects.put("txt_FullName","ID|fullname");
		My_Page_Objects.put("txt_SurName","ID|surname");
		
		My_Page_Objects.put("txt_saID","ID|idnumber");
		
		
		My_Page_Objects.put("rad_Language_English", "XPATH|//md-radio-button[@aria-label='English']");


		


		My_Page_Objects.put("rad_Marital", "XPATH|//md-radio-button[@value='unmarried']");
		My_Page_Objects.put("rad_Marital_Single", "XPATH|//md-radio-button[@value='Single#2']");
		
		My_Page_Objects.put("btn_Next4","ID|submitPersonalForm");
		
		
		//Page 	3
		My_Page_Objects.put("txt_cellNumber","ID|contactNumber"); //0619591240
		
		My_Page_Objects.put("btn_SendOTP","XPATH|//span[text()='Send OTP']");
		
		My_Page_Objects.put("txt_EnterOTP","XPATH|//input[@ng-required='getOTPScreen']");
		My_Page_Objects.put("btn_SubmitOTP","XAPTH|//span[text()='Submit OTP']");
		
		
		
		
		My_Page_Objects.put("txt_LandLine","ID|altNumber");
		My_Page_Objects.put("txt_Email","ID|email");
		
		My_Page_Objects.put("rad_Communication", "XPATH|//md-radio-button[@aria-label='E-mail']");
		
		My_Page_Objects.put("btn_Next5","XPATH|//button[@dsp-page='Your Details']/span[text()='Next']");
		
		
		//Page 4 Address
		My_Page_Objects.put("txt_address1","ID|address1");
		My_Page_Objects.put("txt_address2","ID|address2");
		
		
		My_Page_Objects.put("search_Suburb", "XPATH|//form[@id='addressDetailsForm']/div[1]/div/div[3]/div/div[1]/input");
		My_Page_Objects.put("result_Suburb", "XPATH|//div[@class='subburb-wraper']/div[1]");
		
		My_Page_Objects.put("txt_dd", "XPATH|//input[@name='datefield' and @placeholder='mm']");
		My_Page_Objects.put("txt_mm", "XPATH|//input[@name='datefield' and @placeholder='dd']");
		My_Page_Objects.put("txt_yyyy", "XPATH|//input[@name='datefield' and @placeholder='yyyy']");
		
		
		My_Page_Objects.put("rad_ResidentialOwner", "XPATH|//md-radio-button[@aria-label='Owner']");
		
		My_Page_Objects.put("txt_Amount","ID|residentialAmount");
		
		My_Page_Objects.put("btn_Next6","ID|submitAddressForm");
		
		
		
//		Page 5 Employment 
		My_Page_Objects.put("rad_EmploymentFTE","XPATH|//md-radio-button[@aria-label='Full Time Employed']");
		My_Page_Objects.put("rad_JobAccountant","XPATH|//md-radio-button[@aria-label='Accountant']");
		My_Page_Objects.put("rad_IndustryAgriculture","XPATH|//md-radio-button[@aria-label='Agriculture']");
		My_Page_Objects.put("rad_LevelManagement","XPATH|//md-radio-button[@aria-label='Management']");
		My_Page_Objects.put("txtEmpDate","XPATH|//input[@placeholder='dd' and contains(@ng-blur, 'employmentForm')]");
		My_Page_Objects.put("txtEmpMonth","XPATH|//input[@placeholder='mm' and contains(@ng-blur, 'employmentForm')]");
		My_Page_Objects.put("txtEmpYear","XPATH|//input[@placeholder='yyyy' and contains(@ng-blur, 'employmentForm')]");
		
		
		
		
		My_Page_Objects.put("rad_MatricNo","XPATH|//md-radio-button[@name='is_matric_qualification' and @aria-label='no']");
		My_Page_Objects.put("txt_Employeer","ID|name");
		My_Page_Objects.put("txt_EmpAddress","XPATH|//span[contains(@data-ng-show, 'employmentForm.address1')]/../input");
		My_Page_Objects.put("txt_EmpSuburb","XPATH|//span[contains(@data-ng-show, 'employmentForm.suburb')]/../div[1]/input");
		My_Page_Objects.put("result_EmpSuburb","XPATH|//span[contains(@data-ng-show, 'employmentForm.suburb')]/../div[2]/div[1]");
		My_Page_Objects.put("btn_Next7","XPATH|//button[@dsp-page='Your Finances']");
		
		
		
		//Page 6
		
		My_Page_Objects.put("txt_Income","ID|income");
		My_Page_Objects.put("txt_Expenses","ID|expenses");
		
		My_Page_Objects.put("rad_SourceIncomeSalary","XPATH|//md-radio-button[@aria-label='Salary/wages']");
		My_Page_Objects.put("chk_SourceFundSalary","XPATH|//md-checkbox[@aria-label='Salary/wages']");
		
		My_Page_Objects.put("chk_iConfirm","XPATH|//div[@class='thirdPartyConcent']/md-checkbox");
		My_Page_Objects.put("btn_Next8","ID|incomeSubmitBtn");
		
		//Page 7
		My_Page_Objects.put("chk_ValueAdded","XPATH|//md-checkbox[@aria-label='Credit Smart']");
		My_Page_Objects.put("rad_DebitDay","XPATH|//md-radio-button[@aria-label='3']");
		My_Page_Objects.put("rad_StayTouchNo","XPATH|//*[@ng-model='confirm.rewards']/div/div[3]/div[2]/md-radio-group/md-radio-button");
		
		My_Page_Objects.put("rad_PreferredLang","XPATH|//md-radio-button[@aria-label='English']");
		My_Page_Objects.put("btn_Next9","XPATH|//button[@dsp-page='Product Confirmation and Setup']");
		
		//Page 8
		My_Page_Objects.put("lbl_AccountNumber","XPATH|//div[@class='card-icon']/../p[2]");
		My_Page_Objects.put("fileUpload_ID","XPATH|//label[@ng-hide='idDocSubmitted']");
		My_Page_Objects.put("fileUpload_Address","XPATH|//label[@ng-hide='porDocSubmitted']");
		My_Page_Objects.put("btn_Next10","ID|submitDocsUploadForm");
		
		My_Page_Objects.put("lbl_ApplicationComplete","XPATH|//span[text()='Application complete.']");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		WebDr.page_Objects=My_Page_Objects;
	   
	}
}
