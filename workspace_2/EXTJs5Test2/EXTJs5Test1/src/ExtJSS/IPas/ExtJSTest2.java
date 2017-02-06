package ExtJSS.IPas;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtJSTest2 {
	WebDriver drv = null;
	
	@Before
	public void testinitialize()
	{
		System.setProperty("webdriver.ie.driver", "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		drv = new InternetExplorerDriver();
	}

	@Test
	public void extjstest() { 
		  
		 drv.get("http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout"); 
		 WebDriverWait wait = new WebDriverWait(drv, 500); 
		// String searchByValue = "//label[text()= \"Select a single state:\"]/following-sibling::*[1]/*[2]/*[1]"; 
		 String searchByValue = "((//div[text()=\"Mailing Address\"]/..)/..)/descendant::span[text()=\"State\"]/../following-sibling::*[1]/*[2]/*[2]" ;
		 wait.until(ExpectedConditions.presenceOfElementLocated(By 
		 .xpath(searchByValue))); 
		 drv.findElement(By.xpath(searchByValue)).click();
		  
	//	 ((JavascriptExecutor) drv).executeScript(arg0, arg1)
		 
		 
		 List<WebElement> allwebelem = drv.findElements(By.tagName("li"));
		 for (WebElement el : allwebelem) { 
		 if (el.getText().equalsIgnoreCase("Utah")&& el.isDisplayed()) { 
		 el.click(); 
		 break; 
		 } 
		 } 
		 // TODO Auto-generated method stub 
		  
		 } 
	
	@Test
	public void extjstest2() {

		drv.manage().window().maximize();
		drv.get("http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout");
		WebDriverWait wait = new WebDriverWait(drv, 500);
		// xp1 = //span[text()="State"]/../following-sibling::*[1]/*[2]/*[2]
		// xp2 =
		// //span[text()="Expiration"]/../following-sibling::*[1]//*[2]/*[2]/*[2]
		String searchByValue = "//span[text()=\"Expiration\"]/../following-sibling::*[1]//*[2]/*[2]/*[2]";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchByValue)));
		try {
			drv.findElement(By.xpath(searchByValue)).click();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}
		List<WebElement> allwebelem = drv.findElements(By.tagName("li"));
		for (WebElement el : allwebelem) {
			if (el.getText().equalsIgnoreCase("April") && el.isDisplayed()) {
				el.click();
				break;
			}
		}
		// TODO Auto-generated method stub
	}
	
	@After
	public void closebrowser()
	{
	   //drv.close();
	}
	
	 /*public void findelembyquery()
	{
		String query = cmp.componentquery;
		Component  parent = cmp.parent
				
				while ( parent != null ) {
					
					query = parent.componentQuery + " " + query ;
					parent = parent.parent;
				})
					
					
					String js = "return Ext.ComponentQuery.query( \""+ query + "\" )[0].id;";
		String id = (String) ( ( JavascriptExecutor)drv).executeScript(js);
		
		WebElement elem = drv.findElement(By.id(id));
	}*/

}
