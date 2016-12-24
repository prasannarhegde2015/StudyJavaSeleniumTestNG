package GmailTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

import CommonFunctions.Helper;
import ObjectLibrary.Objectdef;

public class FreescaleWebAnlytics {
	
	public static String baseurl = "https://dev23478.service-now.com";
	public static String iedriverpath = "D:\\Users\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public static String CHROMEdriverpath = "D:\\Users\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
    public static WebDriver globalDriver = null;
    public static String ScreenshotDirectory = "D:\\Users\\Prasanna\\Automation\\Sample Utilities  and Coding\\Java\\RunTest\\Screenshots" ;
    public String colnamesarray = "Testcase;Step;Expected;Actual;Status";
    public static Objectdef obj  = new Objectdef();
	public static Helper hlp = new Helper();
	public static String  resfile = "";
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
		 
		globalDriver.get("http://www.nxp.com/products/microcontrollers-and-processors/arm-processors/kinetis-cortex-m-mcus/e-series-5v-robust-m0-plus-m4/kinetis-ke1xf-168mhz-performance-with-can-5v-microcontrollers-based-on-arm-cortex-m4:KE1xF");
		waitforpgload(globalDriver);          
		globalDriver.findElement(By.linkText("Application Notes")).click();
		waitforpgload(globalDriver);
		globalDriver.get("http://www.nxp.com/products/microcontrollers-and-processors/arm-processors/kinetis-cortex-m-mcus/e-series-5v-robust-m0-plus-m4/kinetis-ke1xf-168mhz-performance-with-can-5v-microcontrollers-based-on-arm-cortex-m4:KE1xF");
		waitforpgload(globalDriver);
		globalDriver.findElement(By.linkText("Sample")).click();
		waitforpgload(globalDriver);
		
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
	//	globalDriver.get(baseurl);
		
	}
	
	public static void  setupdriverIE()
	{
		
	
		System.setProperty("webdriver.ie.driver", iedriverpath);
		globalDriver= new InternetExplorerDriver();
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}
	
	public static void waitforpgload(WebDriver drv) throws InterruptedException
	{
		String status = (String) ((JavascriptExecutor)drv).executeScript("return document.readyState");
		System.out.println("** status*"+status);
		if (status == null)
		{
			System.out.println("** status....chck*"+status);
			status = "Blank";
		}
		while( status.equals("complete") == false)
		{
			status = (String) ((JavascriptExecutor)drv).executeScript("return document.readyState");
			if (status == null)
			{
				System.out.println("** status....chck*"+status);
				status = "Blank";
			}
			System.out.println("** Page complete  "+ status);
			Thread.sleep(1000);
		}
			
	}

}
