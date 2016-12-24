package alertest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerttest {
	
	public static String CHROMEdriverpath = "D:\\Users\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public static WebDriver globalDriver = null;
	public static void main(String[] args)
	{
		setupdriver("http://uat.freescale.com/pages/kinetis-k65-180-mhz-dual-high-speed-full-speed-usbs-2mb-flash-anti-tamper-microcontrollers-mcus-based-on-arm-cortex-m4-core:K65_180");
		enterusernamepassword(globalDriver, "admin","Admin");
	}

	public static void  setupdriver(String baseurl)
	{
		
		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches","--disable-extensions");
		globalDriver= new ChromeDriver(options);
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}
	public static void enterusernamepassword(WebDriver drv, String username, String password)
	   {
		   WebDriverWait wait = new WebDriverWait(drv, 500);
		   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		   alert.authenticateUsing(new UserAndPassword(username, password));
	   }
}
