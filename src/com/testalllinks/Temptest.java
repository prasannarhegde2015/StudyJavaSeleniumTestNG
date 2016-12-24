package com.testalllinks;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Temptest {
	public String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	
	@Test
	public void testmpotest()
	{
		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		WebDriver drv = new ChromeDriver();

		drv.get("D:\\Prasanna\\Automation\\Sample Utilities  and Coding\\Sample HTML forms\\sampletest2.html");
		WebElement elem =  drv.findElement(By.name("txt3"));
		System.out.println("The Max length of textbox: "+elem.getAttribute("maxlength").toString());
		elem.sendKeys("test");
		List<WebElement> allcheckboxes = drv.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement ichk:allcheckboxes )
		{
			if (ichk.isSelected() == false)
			{
				ichk.click();
			}
		}
			
	}

}
