package automationFramework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class MultiBrowser 
{
  public WebDriver driver;
  @Parameters("browser")
  @BeforeClass
  // Passing Browser parameter from TestNG xml
  public void beforeTest(String browser) {
  // If the browser is Firefox, then do this
  if(browser.equalsIgnoreCase("firefox")) {
	  driver = new FirefoxDriver();
  // If browser is IE, then do this	  
  }else if (browser.equalsIgnoreCase("ie")) { 
	  // Here I am setting up the path for my IEDriver
	  System.setProperty("webdriver.ie.driver", "E:\\Prasanna\\CSharp\\SeleniumPOCFramework\\Framework\\Framework\\bin\\Debug\\IEDriverServer.exe");
	  driver = new InternetExplorerDriver();
  }
  else if (browser.equalsIgnoreCase("chrome"))
  {
	  System.setProperty("webdriver.chrome.driver", "E:\\Prasanna\\CSharp\\SeleniumPOCFramework\\Framework\\Framework\\bin\\Debug\\chromedriver.exe");
	  driver = new ChromeDriver();
  }
  // Doesn't the browser type, lauch the Website
  driver.get("http://localhost/lists.html"); 

  }

  // Once Before method is completed, Test method will start

  @Test public void login() throws InterruptedException {

	driver.findElement(By.name("firstname")).sendKeys("Prasanna");
	driver.findElement(By.name("lastname")).sendKeys("Hegde");
	driver.findElement(By.name("email")).sendKeys("Prasannarhegde@gmail.com");
	driver.findElement(By.name("phone")).sendKeys("9768125870");
	driver.findElement(By.name("address")).sendKeys("mumbai");
	driver.findElement(By.name("btnsubmit")).click();
	takescreenshot(driver);
	if ( driver.findElement(By.tagName("body")).getText().contains("Prasanna"))
			{
		       System.out.println("testpassed");		
			}
	}  
     
  @AfterClass public void afterTest() {
		driver.quit();

	}
   public void takescreenshot(WebDriver drv)
   {
	   
	   File scrFile;
		try {
          
			scrFile = ((TakesScreenshot)drv).getScreenshotAs(OutputType.FILE);
		} 
		catch (Exception e){
			System.out.println("Taking screenshot failed for: ");
			// e.printStackTrace();
			return;
		}
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyy-HH-mm-ss");
		String myDateString = sdf.format(myDate);
		 String location = "C:\\temp\\"+myDateString+".jpg";
		try {

			FileUtils.copyFile(scrFile, new File(location));

		} 
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
   }
}