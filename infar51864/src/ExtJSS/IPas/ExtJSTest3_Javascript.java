package ExtJSS.IPas;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtJSTest3_Javascript {
	WebDriver drv = null;
	
	@Before
	public void testinitialize()
	{
		System.setProperty("webdriver.ie.driver", "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		drv = new InternetExplorerDriver();
	}

	
	@Test
	public void extjstest3() {

		drv.manage().window().maximize();
		drv.get("http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout");
		 WebDriverWait wait = new WebDriverWait(drv, 5000);
		// String searchByValue = "//label[text()= \"Select a single state:\"]/following-sibling::*[1]/*[2]/*[1]"; 
		 String searchByValue = "((//div[text()=\"Mailing Address\"]/..)/..)/descendant::span[text()=\"State\"]/../following-sibling::*[1]/*[2]/*[2]" ;
		 wait.until(ExpectedConditions.presenceOfElementLocated(By 
		 .xpath(searchByValue))); 

		// xp1 = //span[text()="State"]/../following-sibling::*[1]/*[2]/*[2]
		// xp2 =
		// //span[text()="Expiration"]/../following-sibling::*[1]//*[2]/*[2]/*[2]

		// TODO Auto-generated method stub
		
/*		var combo = Ext.getCmp("mailingState"); // returns the ComboBox components
		combo.setValue("Utah"); // set the value
		combo.fireEvent("select"); // because setValue() doesn't trigger the event
		
		with (Ext.getCmp("genderComboBox")) { setValue("female"); fireEvent("select"); }
		
		String expr = "//*[regexp:test(@id, 'textfield-[0-9][0-9][0-9][0-9]+-inputEl')]";
driver.findElement(By.xpath(expr));
*/	
		 /* CSS PATH TEST */
		 WebElement input_state = drv.findElement(By.cssSelector("input[name='mailingState'][role='combobox'] "));		 
		 input_state.click();
		 
		 //WebElement block_state = drv.findElement(By.cssSelector("css=div:contains('^State$')"));
		 WebElement block_state = drv.findElement(By.xpath("//div[text()=\'Mailing Address\']//div[@class='x-form-arrow-trigger']"));
		 
		 //WebElement state_arrow = block_state.findElement(By.cssSelector("div[id$='trigger-picker']"));
		 block_state.click();			 
		 
		 
		 	 
		 try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 /*-------------------*/
		 System.out.println("Here now");
		 
		StringBuilder s = new StringBuilder();
//		s.append ("with (Ext.ComponentQuery.query(\"textfield[name='mailingState']\")[0])"
	//			+ "{setValue(arguments[0]);fireEvent('snedKeys(Enter)');}");
		s.append ("return (Ext.ComponentQuery.query(\"textfield[name='firstName']\")[0])");
		//WebElement id = (WebElement) ((JavascriptExecutor) drv).executeScript(s.toString());
		WebElement id = (WebElement) ((JavascriptExecutor) drv).executeScript(s.toString());
		
		System.out.println(id.getText());
		//WebElement element = drv.findElement(By.id(id));
		id.click();
		id.sendKeys("Vishal");
		System.out.println(id.getText());
	
		
	}
	@After
	public void closebrowser()
	{
	   //drv.close();
		drv.quit();
	}

}
