package GmailTest;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import CommonFunctions.Helper;
import ObjectLibrary.GmailLogin;
import ObjectLibrary.MailPage;
import ObjectLibrary.Objectdef;

public class GmailTest {

	public static String baseurl = "https://www.google.com/gmail/about/";
	public static String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public static String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public static WebDriver globalDriver = null;
	public static String ScreenshotDirectory = "E:\\Prasanna\\JavaTutorial\\Screenshots\\Screenshots";

	public static void main(String[] args) throws InterruptedException,
			AWTException {
		// ************************************ Page Objects
		// *************************************https://dev23478.service-now.com/
		GmailLogin pgLogin = new GmailLogin();
		MailPage pgHome = new MailPage();

		Date dtstart = new Date();
		Objectdef obj = new Objectdef();
		Helper hlp = new Helper();
		obj.setName("Parameters.Properties");
		iedriverpath = "";
		CHROMEdriverpath = "";
		iedriverpath = obj.getval("iedriverpath");
		CHROMEdriverpath = obj.getval("chromedriverpath");
		System.out
				.println("************* Browser to be executed is *********: "
						+ obj.getval("browser").toLowerCase());
		System.out
				.println("************* Test Gmail- Email, Send ,Verify ,Delete  Started ************* : "
						+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
								.format(new Date()));

		switch (obj.getval("browser").toLowerCase()) {
		case "ie": {
			setupdriverIE();
			break;
		}
		case "firefox": {
			setupdriver();
			break;
		}
		case "chrome": {
			setupdriver();
			break;
		}
		default: {
			System.out
					.println("Invalid Browser Name Specified in Parameters file Please corret in Parameters.properties");
			break;
		}
		}

		pgLogin.Login(globalDriver, "prasannarhegde", "hpuft11.5");
		pgHome.clickCompose(globalDriver);
		pgHome.sendemail(globalDriver, "prasannarhegde@gmail.com", "Test FAST",
				"Test Fast Contnent");
		pgHome.clickprimarytab(globalDriver);
		pgHome.clickemailwithsubjectline(globalDriver, "Test Fast");
		pgHome.VerifyEmailText(globalDriver, "Test Fast Contnent");
		pgHome.deletemail(globalDriver);
		pgHome.logout(globalDriver);
		Date dtend = new Date();
		long diff = dtend.getTime() - dtstart.getTime();
		System.out
				.println("************* Test Test Gmail- Email, Send ,Verify ,Delete has Ended *************: "
						+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
								.format(new Date()));
		System.out.println("************* Total Time Taken *************: "
				+ (diff / 1000) + " Seconds");
	}

	public static void setupdriver() {

		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches", "--disable-extensions");
		globalDriver = new ChromeDriver(options);
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);

	}

	public static void setupdriverIE() {

		System.setProperty("webdriver.ie.driver", iedriverpath);
		globalDriver = new InternetExplorerDriver();
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);

	}

}
