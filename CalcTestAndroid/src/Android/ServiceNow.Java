package com.servicenow.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;

public class TestLogin {
	public String baseurl = "https://dev21614.service-now.com/";
	public String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public WebDriver drv ;
	WebDriverWait wait ;
	
	@Before
	public void setup()
	{
		
	/*	System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		drv = new ChromeDriver();*/
		System.setProperty("webdriver.ie.driver", iedriverpath);
		drv = new InternetExplorerDriver();

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
		drv.switchTo().frame(drv.findElement(By.cssSelector("iframe[title='Main menu']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Incidents")));
		drv.findElement(By.linkText("Incidents")).click();
		drv.switchTo().defaultContent();
		drv.switchTo().frame(drv.findElement(By.cssSelector("iframe[title='Navigation Content']")));
		//*****click New button
		WebElement btnNew = null;
		try
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sysverb_new")));
		 btnNew = drv.findElement(By.id("sysverb_new"));
		 
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
		drv.findElement(By.id("incident.number")).sendKeys("INC"+strnow);	
		Select sl = new Select(drv.findElement(By.id("incident.impact")));
		sl.selectByVisibleText("2 - Medium");
		//incident.short_description
		drv.findElement(By.id("incident.short_description")).sendKeys("Outllok not working ");	
		//incident.comments
		drv.findElement(By.id("incident.comments")).sendKeys("Outllok not working since morning.... ");	
		//sysverb_insert
		drv.findElement(By.id("sysverb_insert")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("INC"+strnow)));
		drv.findElement(By.linkText("INC"+strnow)).click();
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
	@After
	public void teardown()
	{
		
	}

}
