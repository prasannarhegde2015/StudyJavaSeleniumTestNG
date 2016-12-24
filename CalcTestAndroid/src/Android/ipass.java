import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class testOne {
	WebDriver drv = null;
	
	@Before
	public void setup(){
		//System.setProperty("webdriver.ie.driver", "C:\\Users\\mk92149\\Desktop\\Selenium\\IEDriverServer_Win32_2.53.0\\IEDriverServer.exe");
		//drv = new InternetExplorerDriver();
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mk92149\\Desktop\\Selenium\\chromedriver.exe");
		drv = new ChromeDriver();
	
	}
	
	@Test
	public void extjstest1() throws InterruptedException {
		drv.manage().window().maximize();
		drv.get("https://productapproval.dev.citigroup.net:7080/IPAS2QA/");
		// TODO Auto-generated method stub
			System.out.println("Test RUN ");
			Assert.assertEquals(1, 1);
		WebDriverWait wait = new WebDriverWait(drv,500);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* LOGIN USING VALID CREDENTIALS */
		
		drv.findElement(By.name("USER")).sendKeys("ma26891");
		drv.findElement(By.name("PASSWORD")).sendKeys("Maddy33");
		drv.findElement(By.xpath("//input[@class='ButtonSm']")).click();
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Click on NEw REVIEW From Home screen after login */
		drv.findElement(By.xpath("//div[@class='x-autocontainer-innerCt'][contains(.,'New Review')]")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* FILL IN DETAILS FOR CASE */
		//drv.findElement(By.xpath("//div[@class='x-form-field x-form-required-field x-form-text x-form-text-default '][contains(.,'New Review')]")).click();
		
		//drv.findElement(By.name("governingCommittee")).click();
		
		SelectElementBylabel("Governing ComPAC", "ComPAC LATAM", drv);
		SelectElementBylabel("Presentation Type", "Draft", drv);
		SelectElementBylabel("Covered Initiative Category", "Product", drv);
		
		drv.findElement(By.name("reviewName")).sendKeys("Test Review");
		SelectElementBylabel("Applicable Business Region(s)", "MEXICO", drv);
		
		//String cc = "Save.*Next";
		
		//drv.findElement(By.xpath("//div[@class='x-autocontainer-innerCt'][starts-with(., 'Save') and ends-with(., 'Next')]")).click();
		List< WebElement>  wbm = drv.findElements(By.xpath("//div[@class='x-autocontainer-innerCt'][contains(.,'Next')]")) ;
		
		for ( WebElement ee : wbm)
		{
			System.out.println(ee.getText());
		  if ( ee.getText().contains("Save")  && ee.isDisplayed())
		  {
		     ee.click();
		  }
		}
		// Verification TExt
		
	 Thread.sleep(10000);
	 Thread.sleep(10000);
	 
	  //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'loadmask')]")));
	 String xp2 = "//span[text()=\"Review Status\"]/../following-sibling::*[1]/*[1]";

List< WebElement>  divscol = drv.findElements(By.xpath(xp2)) ;
		
		for ( WebElement ee : divscol )
		{
		//	System.out.println(ee.getText());
		  if ( ee.isDisplayed())
		  {
		    if  ( ee.getText().equals("DRAFT")) 
		    	{
		    	System.out.println("test passed Draft saved output text: "+ee.getText()) ;
		    	break;
		    	}
		    else
		    {
		    	System.out.println("test FAILED Draft saved output text: "+ee.getText()) ;
		    }
		  }
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	
	public void SelectElementBylabel(String labeltext, String strvalue, WebDriver dr) throws InterruptedException
	{
		//String xpt1 = "//span[text()=\"Governing ComPAC\"]/../following-sibling::*[1]/descendant::div[contains(@id,'trigger-picker')]";
								
		String xpt1 = "//span[text()=" +  "\"" +labeltext + "\""+ "]/../following-sibling::*[1]/descendant::div[contains(@id,'trigger-picker')]";
		System.out.println(xpt1);
		dr.findElement(By.xpath(xpt1)).click();
		Thread.sleep(3000);
		List<WebElement> allitems = drv.findElements(By.tagName("li"));
		for(WebElement elem : allitems)
		{
			System.out.println (elem.getText() );
		  if ((elem.getText().equals(strvalue)) && elem.isDisplayed() )
			  
		  {
		       elem.click();
		  }
		}
		
	}
	
	@After
	public void closeBrowser(){
		drv.quit();
	}
	
}
