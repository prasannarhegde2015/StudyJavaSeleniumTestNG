package ServiceNow;

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
import ObjectLibrary.Objectdef;

public class IncidentPage extends SeleniumActions {
	
	public WebDriverWait wait ;
	public Helper hlp = new Helper();
	public Objectdef obj = new Objectdef();
	private String _inctimestampt;
	public  String  resfile = "";
	public  String  resfilehtml = "";
	public String colnamesarray = "Testcase;Step;Expected;Actual;Status";
	public static String pgScreenshotDirectory = "D:\\Prasanna\\Automation\\Sample Utilities  and Coding\\Java\\RunTest\\Screenshots" ;
	public String getInctimestampt() { return this._inctimestampt ;}
	public void setInctimestampt(String Inctimestampt ) { this._inctimestampt = Inctimestampt;}
	
	public IncidentPage()
	{
		System.out.println("Inside Constructor");
		setresfile();
		setresfilehtml();
	}
	public void createnewincident(WebDriver drv)
	{
		obj.setName("\\PageObjects\\ServiceNow\\IncidentPage.Properties");
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
    
    public void VerifyImactvalueonNewis(WebDriver drv , String vincval) throws Exception
    {
    	Select sl2 = new Select(drv.findElement(obj.getBy("selectIncState")));
		System.out.println("The State dropdown value is: "+sl2.getFirstSelectedOption().getText());
		//Verify.verify(sl2.getFirstSelectedOption().getText().equals("New"), "TC_State_Name_Fail", "TC1_FAIL");
	//	org.junit.Assert.assertFalse(message, condition);
		if (sl2.getFirstSelectedOption().getText().equals("New"))
		{
			//System.out.println("Status: INC Impact Value: PASS --- The Incidnet State Value Expected = New , Actual "+sl2.getFirstSelectedOption().getText());
			hlp.createCSVFile(resfile, colnamesarray, "TC01_1;VeifyImpact;New;"+sl2.getFirstSelectedOption().getText()+";Pass");
			hlp.creatextentReport(resfilehtml, colnamesarray, "TC01_1;VeifyImpact;New;"+sl2.getFirstSelectedOption().getText()+";Pass","");
		}
		else
		{
			//System.out.println("Status:INC Impact Value:  FAIL --- The Incidnet State Value Expected = New , Actual "+sl2.getFirstSelectedOption().getText());
			String pgtimestampfilename = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(new Date());
			hlp.CaptureScreesnhot(drv,pgScreenshotDirectory +"\\Image_"+pgtimestampfilename+".jpg");
			hlp.createCSVFile(resfile, colnamesarray, "TC01_1;VeifyImpact;New;"+sl2.getFirstSelectedOption().getText()+";Fail");
			hlp.creatextentReport(resfilehtml, colnamesarray, "TC01_1;VeifyImpact;New;"+sl2.getFirstSelectedOption().getText()+";Pass",pgScreenshotDirectory +"\\Image_"+pgtimestampfilename+".jpg");
		}
		
    }
   
    public void verifyTextDesc(WebDriver drv , String vincval) throws Exception
    {
    	Thread.sleep(5000);
    	WebElement elem = drv.findElement(By.xpath("//div[contains(text(),\'"+vincval+"')]"));
    	String longdesctxt = elem.getText();
    	
		//Verify.verify(sl2.getFirstSelectedOption().getText().equals("New"), "TC_State_Name_Fail", "TC1_FAIL");
	//	org.junit.Assert.assertFalse(message, condition);
		if (longdesctxt.equals(vincval))
		{
			System.out.println("Status: INC Impact Value: PASS --- The Incidnet State Value Expected = " +vincval +" , Actual "+longdesctxt );
			hlp.createCSVFile(resfile, colnamesarray, "TC01_2;Veify Incident Value;"+vincval+";"+longdesctxt+";Pass");
			hlp.creatextentReport(resfilehtml, colnamesarray, "TC01_2;Veify Incident Value;"+vincval+";"+longdesctxt+";Pass","");
		}
		else
		{
			
			SeleniumActions acn = new SeleniumActions();
			acn.scroll(drv, elem);
		//	acn.scroll_Page(drv, elem , 200);
			String pgtimestampfilename = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(new Date());
			hlp.CaptureScreesnhot(drv,pgScreenshotDirectory +"\\Image_"+pgtimestampfilename+".jpg");
			System.out.println("Status:INC Impact Value:  FAIL --- The Incidnet State Value Expected = " +vincval + " , Actual "+longdesctxt );
			hlp.createCSVFile(resfile, colnamesarray, "TC01_2;Veify Incident Value;"+vincval+";"+longdesctxt+";Fail");
			hlp.creatextentReport(resfilehtml, colnamesarray, "TC01_2;Veify Incident Value;"+vincval+";"+longdesctxt+";Fail",pgScreenshotDirectory +"\\Image_"+pgtimestampfilename+".jpg");
			
		}
    }
    
    public void deleteIncident(WebDriver drv )
    {
    	drv.findElement(obj.getBy("btndelete")).click();
    	wait.until(ExpectedConditions.presenceOfElementLocated( obj.getBy("btnokdel")));
    	drv.findElement(obj.getBy("btnokdel")).click();
    }

    public  void setresfile()
	{
		try 
		{
		obj.setName("\\src\\ObjectLibrary\\Parameters.Properties");
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss").format(new Date());
		System.out.println("Res folder "+obj.getval("resultfolder"));
		resfile = obj.getval("resultfolder") + "\\result"+timestamp+".csv";
		System.out.println("Res file  "+resfile );
		}
		catch(Exception ex)
		{
			System.out.println("Excfeption creating name of resulffile "+ex.getMessage().toString());
		}
	}
    public  void setresfilehtml()
	{
		try 
		{
		obj.setName("\\src\\ObjectLibrary\\Parameters.Properties");
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss").format(new Date());
		System.out.println("Res folder "+obj.getval("resultfolder"));
		resfilehtml = obj.getval("resultfolder") + "\\result"+timestamp+".html";
		System.out.println("Res file  "+resfilehtml );
		}
		catch(Exception ex)
		{
			System.out.println("Excfeption creating name of resulffileHTML "+ex.getMessage().toString());
		}
	}

}
