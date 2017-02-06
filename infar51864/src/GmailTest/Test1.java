package GmailTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;  
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import CommonFunctions.Helper;
import LTIWorkPlace.LTILoginPage;
import ObjectLibrary.Objectdef;

public class Test1 {
	
	
	public static String baseurl = "https://lntinfotech.facebook.com/";
	public static String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public static String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
    public static WebDriver globalDriver = null;
    public static String ScreenshotDirectory = "D:\\Prasanna\\Automation\\Sample Utilities  and Coding\\Java\\RunTest\\Screenshots" ;
	
    public  void  setupdriver()
	{
		
		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches","--disable-extensions");
		globalDriver= new ChromeDriver(options);
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}
	
	public  void  setupdriverIE()
	{
		
	
		System.setProperty("webdriver.ie.driver", iedriverpath);
		globalDriver= new InternetExplorerDriver();
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}

	
	public  void teardowndriver()
	{
		
	     globalDriver.close();
	     globalDriver.quit();
	}
    
	@Test
	
	public void test1() throws Exception
	{
	         
		 LTILoginPage pgLogin = new LTILoginPage();
		 Date dtstart = new Date();
		 Objectdef obj  = new Objectdef();
		 Helper hlp = new Helper();
		 obj.setName("\\src\\ObjectLibrary\\Parameters.Properties");
		 iedriverpath ="";
		 CHROMEdriverpath  = "";
		 iedriverpath =  obj.getval("iedriverpath");
		 CHROMEdriverpath = obj.getval("chromedriverpath");
		 System.out.println("************* Browser to be executed is *********: "+obj.getval("browser").toLowerCase());
		 System.out.println("************* Test Gmail- Email, Send ,Verify ,Delete  Started ************* : "+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
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
		 
		pgLogin.Login(globalDriver,"prasanna.hegde@lntinfotech.com","Lowis2016OCT;"); 
		pgLogin.verifyFname(globalDriver, "Prasanna");
		Date dtend = new Date();
		long diff = dtend.getTime() - dtstart.getTime();
		System.out.println("************* Test Test Gmail- Email, Send ,Verify ,Delete has Ended *************: "+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		System.out.println("************* Total Time Taken *************: "+ (diff /1000)+ " Seconds");
		teardowndriver();
	}
	

	public String strJoin(String[] aArr, String sSep) {
	    StringBuilder sbStr = new StringBuilder();
	    for (int i = 0, il = aArr.length; i < il; i++) {
	        if (i > 0)
	            sbStr.append(sSep);
	        sbStr.append(aArr[i]);
	    }
	    return sbStr.toString();
	}

    public void dofileoperations() throws IOException
 {
	 File myfile = new File("d:\\test1.txt");
     FileOutputStream out = new FileOutputStream(myfile);
     String test = "["+new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date())+"] Test1 Method was executed ";
     byte[] b = test.getBytes();
     out.write(b);
     out.close();
     String abc = "a;b;c;d;e";
     String[] arrabc = abc.split(";");
     for(String in:arrabc)
     {
    	 System.out.println("Elelment "+in);
     }
     System.out.println("Elelment "+ strJoin(arrabc, "."));
 }
     
    @Test
    public void   testregexp()
    {
    	System.out.println(Pattern.matches("\\D+\\d{1,3}", "Prasanna15"));
    }
    
}
