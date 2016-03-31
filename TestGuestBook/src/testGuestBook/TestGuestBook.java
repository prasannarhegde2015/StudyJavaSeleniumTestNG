package testGuestBook;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestGuestBook {
	
	public WebDriver driver;
@BeforeMethod
@Parameters("browser")
public void testinitialize(String browser)
{
   if (  browser.equalsIgnoreCase("firefox"))
   {
       driver = new FirefoxDriver();
      
   }
   else if (browser.equalsIgnoreCase("ie"))
   {
      System.setProperty(key, value)
   }
}
	
		// TODO Auto-generated method stub

@AfterMethod
public void testclose()
{
	
}

	
	 
}
