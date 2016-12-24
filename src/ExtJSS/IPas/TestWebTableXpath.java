package ExtJSS.IPas;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWebTableXpath {
    public String baseurl = "file:///D:/Prasanna/Automation/Sample%20Utilities%20%20and%20Coding/Sample%20HTML%20forms/sampletest1.html";
    public String chromedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	WebDriver drv = null;	
	@Before
	public void testinitialize()
	{
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		drv = new ChromeDriver();
		drv.get(baseurl); 
	}

	@Test
	public void testwebtable() { 
		  
		 
		 @SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(drv, 500); 
		 WebElement  tble =  drv.findElement(By.id("fixed"));
		 
	   List<WebElement> allrows = tble.findElements(By.tagName("tr"));
	   for( WebElement tblrow: allrows)
	   {
		   List<WebElement> allcols = tblrow.findElements(By.tagName("td"));
		   if  ( allcols.get(0).getText().equalsIgnoreCase("customer2") )
		   {
			   allcols.get(1).click();
			   break;
		   }
		   
	   }
		 System.out.println("Message from warning is: "+drv.switchTo().alert().getText().toString());
	  //   Assert.assertEquals("Customer2", drv.switchTo().alert().getText());
	     drv.switchTo().alert().accept();
		 drv.findElement(By.xpath("//td[contains(.,\"Save & Next\")]")).click();
		 drv.switchTo().alert().accept();
		 drv.findElement(By.xpath("//td[contains(.,\"Customer1\")]/../td[contains(.,\"Click\")]")).click();
		 System.out.println("Message from warning is: "+drv.switchTo().alert().getText().toString());
		 drv.switchTo().alert().accept();
		//
		 } 
	
	
	
	
	@After
	public void closebrowser()
	{
	  // drv.close();
	}
	
}
