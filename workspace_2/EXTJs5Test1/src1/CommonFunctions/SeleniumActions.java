package CommonFunctions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumActions {
	
	
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

}
