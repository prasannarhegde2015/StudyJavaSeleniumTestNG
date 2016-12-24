package CommonFunctions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

public class SeleniumActions {
	
	
	public void movetoelem(WebDriver dr, WebElement el)
	{
		Actions  acn = new Actions(dr);
		acn.moveToElement(el).build().perform();
	//	acn.moveToElement(el, 50, 50);
	}
	public void robotmousemove(WebDriver dr, WebElement el) throws AWTException, InterruptedException
	{
		org.openqa.selenium.Point point = el.getLocation();
		int xcord = point.getX();
        System.out.println("Element's Position from left side Is "+xcord +" pixels.");
        int ycord = point.getY();
		Robot r = new Robot();//construct a Robot object for default screen
		r.mouseMove(xcord, ycord);
	    Thread.sleep(2000);
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

	
	public  boolean scroll_Page(WebDriver drv, WebElement webelement, int scrollPoints)
	{
		
		//JavascriptExecutor jse = (JavascriptExecutor)drv;
		//jse.executeScript("window.scrollBy(0,250)", "");
		//jse.executeScript("scroll(0, 250);");
		//Identify the WebElement which will appear after scrolling down
	//	WebElement element = driver.findElement(By.xpath(".//*[@id='mCSB_3_container']/p[3]"));
		// now execute query which actually will scroll until that element is not appeared on page.
	//	je.executeScript("arguments[0].scrollIntoView(true);",element);
	    try
	    {               
	        Actions dragger = new Actions(drv);
	        // drag downwards
	        int numberOfPixelsToDragTheScrollbarDown = 10;
	      //  for (int i = 10; i < scrollPoints; i = i + numberOfPixelsToDragTheScrollbarDown)
	      //  {
	            dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(webelement).build().perform();
	      //  }
	        Thread.sleep(500);
	        return true;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return false;
	    }
	}

    public void scroll(WebDriver driver,WebElement e) throws InterruptedException
    {
    	Coordinates cor=((Locatable)e).getCoordinates();
    	cor.inViewPort();
    	Thread.sleep(1000);
    	
    }

}
