package ObjectLibrary;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonFunctions.Helper;
import CommonFunctions.SeleniumActions;

public class HomePage extends SeleniumActions {
	
	Helper hlp = new Helper();
	Objectdef obj  = new Objectdef();
	public void ClickHomePanelLink(WebDriver drv , String linkname) throws InterruptedException
	{
		try 
		{
		obj.setName("HomePage.Properties");
		WebDriverWait wait ;
		wait = new WebDriverWait(drv, 500);
		drv.switchTo().defaultContent();
		wait(5);
	//*****click Incidents Link 
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(obj.getBy("frmNavigationFrame")));
		drv.switchTo().defaultContent();
		wait(2);
		drv.switchTo().frame(drv.findElement(obj.getBy("frmMainMenu")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkname)));
		drv.findElement(By.linkText(linkname)).click();
		drv.switchTo().defaultContent();
		}
		catch(NoSuchElementException ex)
		{
			System.out.println("[ClickHomePanelLink] --No Such element "+ex);
		}
		catch(StaleElementReferenceException ex)
		{
			System.out.println("[ClickHomePanelLink] --Element not attached to WebPage "+ex);
		}
		catch(Exception ex)
		{
			System.out.println("[ClickHomePanelLink] --Other Exception "+ex);
		}
	}
	
	public void performLogout(WebDriver drv)
	{
		hlp.setName("HomePage.Properties");
		drv.switchTo().defaultContent();
		drv.findElement(obj.getBy("btnlogout")).click();
		drv.close();
		drv.quit();
	}
	
	
	
	  
	

}
