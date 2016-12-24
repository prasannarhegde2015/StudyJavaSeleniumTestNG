package GmailWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonFunctions.Helper;
import ObjectLibrary.Objectdef;

public class GmailLogin {
	
	Helper hlp = new Helper();
	Objectdef obj  = new Objectdef();
	public void Login(WebDriver drv, String Username, String Password) throws InterruptedException
	{
		obj.setName("GmailLogin.Properties");
		WebDriverWait wait ;
		wait = new WebDriverWait(drv, 500);
		drv.findElement(obj.getBy("lnksignin")).click();
        drv.findElement(obj.getBy("txtemail")).sendKeys(Username);
        drv.findElement(obj.getBy("btnnext")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(obj.getBy("txtpassword")));
        Thread.sleep(1000);
        drv.findElement(obj.getBy("txtpassword")).sendKeys(Password);
        drv.findElement(obj.getBy("btnsignin")).click();
	}

}
