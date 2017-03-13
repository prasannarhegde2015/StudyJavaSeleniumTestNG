package ObjectLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import CommonFunctions.Helper;
import CommonFunctions.SeleniumActions;

public class IncidentPage extends SeleniumActions {
	
	public WebDriverWait wait ;
	Helper hlp = new Helper();
	Objectdef obj = new Objectdef();
	private String _inctimestampt;
	
	public String getInctimestampt() { return this._inctimestampt ;}
	public void setInctimestampt(String Inctimestampt ) { this._inctimestampt = Inctimestampt;}
	
	
	public void createnewincident(WebDriver drv)
	{
		obj.setName("IncidentPage.Properties");
		wait = new WebDriverWait(drv, 500);
		drv.switchTo().frame(drv.findElement(obj.getBy("frmNavigationFrame")));
		//*****click New button
		 System.out.println("Time before button found"+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		WebElement btnNew = null;
		try
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(obj.getBy("btnNew")));
			btnNew = drv.findElement(obj.getBy("btnNew"));
			System.out.println("Time after button found"+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
					.format(new Date()));
		}
		catch(NoSuchElementException e)
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
	}

	public void enterIncidentNumber(WebDriver drv, String incnum) throws InterruptedException
	{
		hlp.setName("IncidentPage.Properties");
		String now = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
				.format(new Date());
				String strnow = now.toString();
		wait.until(ExpectedConditions.presenceOfElementLocated( obj.getBy("txtfldIncidentNumber")));
		Thread.sleep(2000);
		drv.findElement(obj.getBy("txtfldIncidentNumber")).clear();
		drv.findElement(obj.getBy("txtfldIncidentNumber")).sendKeys(incnum+strnow);
		setInctimestampt(strnow);
	}
    

	public void enterimpact(WebDriver drv , String incval)
    {
    	Select sl = new Select(drv.findElement(obj.getBy("selectIncImpact")));
		sl.selectByVisibleText(incval);
		
    }

    public void entershortdesc(WebDriver drv , String shortdesc)
   {
	   drv.findElement(obj.getBy("txtareaIncshortdesc")).sendKeys(shortdesc);
   }
    
    public void enterlongdesc(WebDriver drv , String longdesc)
    {
    	drv.findElement(obj.getBy("txtareaFullDec")).sendKeys(longdesc);
    }
    
    public void submitIncident(WebDriver drv)
    {
    	drv.findElement(obj.getBy("btnIncSubmit")).click();
    }
   
    public void ClickNewlycreatedLink(WebDriver drv, String shortdesc) throws InterruptedException
    {
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(shortdesc+getInctimestampt())));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(shortdesc+getInctimestampt())));
		if (isstale(drv, drv.findElement(By.linkText(shortdesc+getInctimestampt()))) )
		{
		   wait(5);
		   drv.findElement(By.linkText(shortdesc+getInctimestampt())).click();
		}
    }
    
    public void VerifyImactvalueonNewis(WebDriver drv , String vincval)
    {
    	Select sl2 = new Select(drv.findElement(obj.getBy("selectIncState")));
		System.out.println("The State dropdown value is: "+sl2.getFirstSelectedOption().getText());
		//Verify.verify(sl2.getFirstSelectedOption().getText().equals("New"), "TC_State_Name_Fail", "TC1_FAIL");
	//	org.junit.Assert.assertFalse(message, condition);
		if (sl2.getFirstSelectedOption().getText().equals("New"))
		{
			System.out.println("Status: INC Impact Value: PASS --- The Incidnet State Value Expected = New , Actual "+sl2.getFirstSelectedOption().getText());
		}
		else
		{
			System.out.println("Status:INC Impact Value:  FAIL --- The Incidnet State Value Expected = New , Actual "+sl2.getFirstSelectedOption().getText());
		}
		
    }
   
    public void verifyTextDesc(WebDriver drv , String vincval)
    {
    	String longdesctxt = drv.findElement(By.xpath("//div[contains(text(),\'"+vincval+"')]")).getText();
		//Verify.verify(sl2.getFirstSelectedOption().getText().equals("New"), "TC_State_Name_Fail", "TC1_FAIL");
	//	org.junit.Assert.assertFalse(message, condition);
		if (longdesctxt.equals(vincval))
		{
			System.out.println("Status: INC Impact Value: PASS --- The Incidnet State Value Expected = " +vincval +" , Actual "+longdesctxt );
		}
		else
		{
			System.out.println("Status:INC Impact Value:  FAIL --- The Incidnet State Value Expected = " +vincval + " , Actual "+longdesctxt );
		}
    }
    
    public void deleteIncident(WebDriver drv )
    {
    	drv.findElement(obj.getBy("btndelete")).click();
    	wait.until(ExpectedConditions.presenceOfElementLocated( obj.getBy("btnokdel")));
    	drv.findElement(obj.getBy("btnokdel")).click();
    }
}
