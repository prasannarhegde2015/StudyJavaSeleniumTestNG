package com.servicenow.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class TestLogin {
	public String baseurl = "https://dev23478.service-now.com";
	public String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public WebDriver drv ;
	WebDriverWait wait ;
	
	@Before
	public void setup()  
	{
		
		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		drv = new ChromeDriver();
		/*System.setProperty("webdriver.ie.driver", iedriverpath);
		drv = new InternetExplorerDriver();*/

		drv.manage().window().maximize();
		drv.get(baseurl);
		wait = new WebDriverWait(drv, 500);
		
	}
	@Test
	public void createincident() throws InterruptedException
	{
		login();
		drv.switchTo().defaultContent();
		
		//*****click Incidents Link 
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='Navigation Content']")));
		drv.switchTo().defaultContent();
		drv.switchTo().frame(drv.findElement(By.cssSelector("iframe[title='Main menu']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Incidents")));
		drv.findElement(By.linkText("Incidents")).click();
		drv.switchTo().defaultContent();
		drv.switchTo().frame(drv.findElement(By.cssSelector("iframe[title='Navigation Content']")));
		//*****click New button
		
		 System.out.println("Time before button found"+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		WebElement btnNew = null;
		try
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sysverb_new")));
		 btnNew = drv.findElement(By.id("sysverb_new"));
		 System.out.println("Time after button found"+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		}catch(NoSuchElementException e)
		{
			System.out.println("Elenent not found");
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("StaleElement ref errro");
		}
		catch(Exception e)
		{
			System.out.println("Other Error");
		}
		if (btnNew != null )
		{
			System.out.println("New button was found");
			movetoelem(drv, btnNew );
			clickelem(drv, btnNew );
	//	drv.findElement(By.id("sysverb_new")).click();
		}
		else
		{
			System.out.println("Elenent not found: Test Aborted");
			return;
		}
	//	String js = "return GlideList2.get('incident').action('80c8c3a60a0a0b34003364513f7d9d27', 'sysverb_new');";
	//	((JavascriptExecutor)drv.switchTo().frame(drv.findElement(By.cssSelector("iframe[class='ui-layout-pane ui-layout-pane-center']")))).executeScript(js);
		String now = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
				.format(new Date());
				String strnow = now.toString();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("incident.number")));
		Thread.sleep(2000);
		drv.findElement(By.id("incident.number")).clear();
		drv.findElement(By.id("incident.number")).sendKeys("INC"+strnow);	
		Select sl = new Select(drv.findElement(By.id("incident.impact")));
		sl.selectByVisibleText("2 - Medium");
		//incident.short_description
		drv.findElement(By.id("incident.short_description")).sendKeys("Outllok not working ");	
		//incident.comments
		drv.findElement(By.id("incident.comments")).sendKeys("Outllok not working since morning.... ");	
		//sysverb_insert
		drv.findElement(By.id("sysverb_insert")).click();
	//	wait.until(ExpectedConditions.stalenessOf(drv.findElement(By.linkText("INC"+strnow))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("INC"+strnow)));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("INC"+strnow)));
		if (isstale(drv, drv.findElement(By.linkText("INC"+strnow))) )
		{
		   wait(5);
		   drv.findElement(By.linkText("INC"+strnow)).click();
		}
		//incident.state
		Select sl2 = new Select(drv.findElement(By.id("incident.state")));
		System.out.println("The State dropdown value is: "+sl2.getFirstSelectedOption().getText());
		Verify.verify(sl2.getFirstSelectedOption().getText().equals("New"), "TC_State_Name_Fail", "TC1_FAIL");
		
		
	}
	
	
	public void login()
	{
		try
		{
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_name")));
		drv.switchTo().frame(0);
		drv.findElement(By.id("user_name")).clear();
		drv.findElement(By.id("user_name")).sendKeys("admin");
		drv.findElement(By.id("user_password")).clear();
		drv.findElement(By.id("user_password")).sendKeys("ServiceNow97bd916$");
		//drv.findElement(By.id("user_password")).sendKeys("kWu5cLfuTare");
		
		drv.findElement(By.id("sysverb_login")).click();
		}
		catch( NoSuchElementException e)
		{
			System.out.println(" No such elemnt");
		}
		catch ( StaleElementReferenceException e)
		{
			System.out.println(" Element not attached to page");
		}
		catch(Exception e)
		{
			System.out.println(" Other Exception "+e);
		}
		
	}
	
	public void movetoelem(WebDriver dr, WebElement el)
	{
		Actions  acn = new Actions(dr);
		acn.moveToElement(el).build().perform();
	}
	public void clickelem(WebDriver dr, WebElement el)
	{
		Actions  acn = new Actions(dr);
		acn.click(el).build().perform();
	}
	
	public boolean isstale(WebDriver drv , WebElement el)
	{
		 try 
		 {
			el.click();
			return false;
		 }
		 catch(StaleElementReferenceException ex)
		 {
			 return true;
		 }
		 
	}
	
	public void takescreenshot(WebDriver dr, String screenshotfile)
	{
		@SuppressWarnings("unused")
		File srcfile ;
		srcfile =  ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
	//	Fi
		

	}
	
	public void wait(int secs) throws InterruptedException
	   {
		   Thread.sleep(secs*1000);
	   }
	
	public void  sendkeys() throws AWTException
	{
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_KP_DOWN);
		
		
	}
	
	@SuppressWarnings("unused")
	private void type(String s) throws AWTException
	  {
		Robot robot = new Robot();
	    byte[] bytes = s.getBytes();
	    for (byte b : bytes)
	    {
	      int code = b;
	      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
	      if (code > 96 && code < 123) code = code - 32;
	      robot.delay(40);
	      robot.keyPress(code);
	      robot.keyRelease(code);
	    }
	  }
	@After
	public void teardown()
	{
		drv.switchTo().defaultContent();
		drv.findElement(By.xpath(".//button[text()=\"Logout\"]")).click();
		drv.close();
		drv.quit();
	}

}
