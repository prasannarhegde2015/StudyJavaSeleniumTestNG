package ExtJSS.IPas;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import CommonFunctions.Helper;
import ObjectLibrary.Objectdef;

public class MainClass {
	
	public static String baseurl = "https://dev23478.service-now.com";
	public static String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public static String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
    public static WebDriver globalDriver = null;
    public static String ScreenshotDirectory = "D:\\Prasanna\\Automation\\Sample Utilities  and Coding\\Java\\RunTest\\Screenshots" ;
    public String colnamesarray = "Testcase;Step;Expected;Actual;Status";
    public static Objectdef obj  = new Objectdef();
	public static Helper hlp = new Helper();
	public static String  resfile = "";
	public static String  resfilehtml = "";
	public static void main(String[] args) throws Exception
	{
		//************************************ Page Objects *************************************https://dev23478.service-now.com/
		 ServiceNow.LoginPage pgLogin = new ServiceNow.LoginPage();
		 ServiceNow.HomePage pgHome = new ServiceNow.HomePage();
		 ServiceNow.IncidentPage pgInc = new ServiceNow.IncidentPage();
		 Date dtstart = new Date();
		
		 obj.setName("\\src\\ObjectLibrary\\Parameters.Properties");
		 iedriverpath ="";
		 CHROMEdriverpath  = "";
		 iedriverpath =  obj.getval("iedriverpath");
		 CHROMEdriverpath = obj.getval("chromedriverpath");
		 System.out.println("************* Browser to be executed is *********: "+obj.getval("browser").toLowerCase());
		 System.out.println("************* Test has Started ************* : "+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		 
		 switch (obj.getval("browser").toLowerCase())
		 {
		 case "ie" : 
			 {
				 setupdriverIE();
			  break;
			 }
		 case "firefox" : 
			 {
				 setupdriver();
				 break;
			 }
		 case "chrome" : 
			 {
				 setupdriver();
				 break;
			 }
		 default :  
			 {
				 System.out.println("Invalid Browser Name Specified in Parameters file Please corret in Parameters.properties");
				 break;
			 }
		 }
		 
		
		pgLogin.login(globalDriver,"admin","ServiceNow97bd916$");		
	
		pgHome.ClickHomePanelLink(globalDriver, "Incidents");
		
		pgInc.createnewincident(globalDriver);
		pgInc.enterIncidentNumber(globalDriver, "IMC");
		
		pgInc.entershortdesc(globalDriver, "Incident number..");
		pgInc.enterimpact(globalDriver, "2 - Medium");
		pgInc.enterlongdesc(globalDriver, "Incident number..Detailed Comments ...");
		pgInc.submitIncident(globalDriver);
		String timestampfilename = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(new Date());
		pgInc.ClickNewlycreatedLink(globalDriver,"IMC");
		
		try {
			hlp.CaptureScreesnhot(globalDriver, ScreenshotDirectory +"\\Image_"+timestampfilename+".jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pgInc.VerifyImactvalueonNewis(globalDriver, "2 - Medium");
		pgInc.verifyTextDesc(globalDriver, "Detailed Comments");
		pgInc.deleteIncident(globalDriver);
		pgHome.performLogout(globalDriver);
		Date dtend = new Date();
		long diff = dtend.getTime() - dtstart.getTime();
		System.out.println("************* Test has Ended *************: "+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		System.out.println("************* Total Time Taken *************: "+ (diff /1000)+ " Seconds");
		
	}
	
	public static void  setupdriver()
	{
		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches","--disable-extensions");
		globalDriver= new ChromeDriver(options);
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}
	
	public static void  setupdriverIE()
	{
		
		System.setProperty("webdriver.ie.driver", iedriverpath);
		globalDriver= new InternetExplorerDriver();
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}

	
}
