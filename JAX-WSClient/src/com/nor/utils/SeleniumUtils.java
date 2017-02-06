package com.nor.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
	
	public void clickElem( WebElement el,String elname)
	{
		
		try 
		{
			el.click();
			System.out.println("Element was clicked: "+elname);
		}
		catch(NoSuchElementException ex)
		{
			System.out.println("No Such Elelment exception"+ex);
		}
		catch(StaleElementReferenceException ex)
		{
			System.out.println("Stale  Elelment exception"+ex);
		}
		catch(Exception ex)
		{
			System.out.println("Other Expcetion"+ex);
		}
	}
	
	public void enterValue(WebElement el,String elname,String val)
	{
		try 
		{
			el.clear();
			el.sendKeys(val);
			System.out.println("Value "+val+"set in : "+elname);
		}
		catch(NoSuchElementException ex)
		{
			System.out.println("No Such Elelment exception"+ex);
		}
		catch(StaleElementReferenceException ex)
		{
			System.out.println("Stale  Elelment exception"+ex);
		}
		catch(Exception ex)
		{
			System.out.println("Other Expcetion"+ex);
		}
	}
	
	public void Switchtoframe(WebDriver dr, WebElement el)
	{
		WebDriverWait wt = new WebDriverWait(dr, 500);
		try
		{
		System.out.println("Sel Utils: Switch to Farme");
		wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(el));
		}
		catch(NoSuchFrameException ex)
		{
			System.out.println("No Such Frame"+ex);
		}
	}
	public void SwitchtoDelfault(WebDriver dr)
	{
		try
		{
		System.out.println("Sel Utils Switch to default");
		dr.switchTo().defaultContent();
		}
		catch(Exception ex)
		{
			System.out.println("No Such Frame"+ex);
		}
	}
    
	public void waitforPresenseofElement( WebDriver drv,final WebElement el)
	
	{
		System.out.println("Sel Utils: Wait for Elm to be present");
		
		WebDriverWait wt = new WebDriverWait(drv, 500);
		wt.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver drv) {
                try {
                     el.isDisplayed();
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                } catch(StaleElementReferenceException e)
                {
                	return false;
                }
            }
        });
		
	}
	
	
   public  void waitforpgload(WebDriver drv) throws InterruptedException
	{
		String status = (String) ((JavascriptExecutor)drv).executeScript("return document.readyState");
		System.out.println("** status*"+status);
		if (status == null)
		{
			System.out.println("** status....chck*"+status);
			status = "Blank";
		}
		while( status.equals("complete") == false)
		{
			status = (String) ((JavascriptExecutor)drv).executeScript("return document.readyState");
			if (status == null)
			{
				System.out.println("** status....chck*"+status);
				status = "Blank";
			}
			System.out.println("** Page complete  "+ status);
			Thread.sleep(1000);
		}
		System.out.println("** Out of Page Load");
	}
}
