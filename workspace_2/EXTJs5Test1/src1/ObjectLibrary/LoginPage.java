package ObjectLibrary;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public void login(WebDriver drv,String username, String passowrd)
	{
		WebDriverWait wait ;
		Objectdef obj  = new Objectdef();
		try
		{
			
			obj.setName("LoginPage.Properties");
			wait = new WebDriverWait(drv, 500);
	//	));
		drv.switchTo().frame(0);
		wait.until(ExpectedConditions.presenceOfElementLocated(obj.getBy("txtusername")));
		drv.findElement(obj.getBy("txtusername")).clear();
		drv.findElement(obj.getBy("txtusername")).sendKeys(username);
		drv.findElement(obj.getBy("txtpassword")).clear();
		drv.findElement(obj.getBy("txtpassword")).sendKeys(passowrd);
		drv.findElement(obj.getBy("btnlogin")).click();
		}
		catch( NoSuchElementException e)
		{
			System.out.println(" No such element");
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
}
