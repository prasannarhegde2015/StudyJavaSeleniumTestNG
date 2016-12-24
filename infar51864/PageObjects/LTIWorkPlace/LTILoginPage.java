package LTIWorkPlace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import CommonFunctions.Helper;
import ObjectLibrary.Objectdef;
import com.testalllinks.HelperUtils;

public class LTILoginPage {

	
	Helper hlp = new Helper();
	Objectdef obj  = new Objectdef();
	public String  holder = "";
	public String  resfile = "";
	
	public String colnamesarray = "Testcase;Step;Expected;Actual;Status";
	public void setresfile()
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
	public void Login(WebDriver drv, String Username, String Password) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(drv, 500);
		obj.setName("\\PageObjects\\LTIWorkPlace\\LTIlogin.Parameters");
		
		drv.findElement(obj.getBy("lnksignin")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(obj.getBy("txtusername")));
	    Thread.sleep(1000);
        drv.findElement(obj.getBy("txtusername")).sendKeys(Username);
        drv.findElement(obj.getBy("txtpassword")).sendKeys(Password);
        drv.findElement(obj.getBy("btnsignin")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(obj.getBy("txtpasssword2")));
        drv.findElement(obj.getBy("txtpasssword2")).sendKeys(Password);
        drv.findElement(obj.getBy("btnSignin2")).click();
        
	    Thread.sleep(1000);
	}
	
	public void verifyFname(WebDriver drv,String fname) throws Exception
	{
		setresfile();
		WebDriverWait wait = new WebDriverWait(drv, 500);
		HelperUtils hutil = new HelperUtils();
		obj.setName("\\PageObjects\\LTIWorkPlace\\LTIlogin.Parameters");
		
		replacelocatorText("\\PageObjects\\LTIWorkPlace\\LTIlogin.Parameters", "fname", fname);
		wait.until(ExpectedConditions.presenceOfElementLocated(obj.getBy("fname")));
		String actname =drv.findElement(obj.getBy("fname")).getText();
		if (actname.equals(fname))
		{
		System.out.println("CSV file value "+resfile);
		 hutil.createCSVFile(resfile, colnamesarray, "TCLogin;VeifyUserFirstName;"+fname+";"+actname+";Pass");
		}
		else
		{
		 hutil.createCSVFile(resfile, colnamesarray, "TCLogin;VeifyUserFirstName;"+fname+";"+actname+";Fail");
		}
		revertlocatorText("\\PageObjects\\LTIWorkPlace\\LTIlogin.Parameters","fname");
	}

    public void replacelocatorText(String ParamFile ,String objectName,String replaceValue)
   {
	    hlp.setName(ParamFile);
		String prstvalue = hlp.readpropertiesfileall().getProperty(objectName);
		holder = prstvalue;
		System.out.println("Old Vlaue: "+holder);
		prstvalue = prstvalue.replace("<placeholder>", replaceValue);
		System.out.println("New Vlaue: "+prstvalue);
		hlp.updatePropertyValue(objectName, prstvalue);
   }
   
    public void revertlocatorText(String ParamFile,String objectName)
    {
    	hlp.setName(ParamFile);
    	System.out.println("Global Variable Vlaue:"+holder);
    	hlp.updatePropertyValue(objectName, holder);
    }

    
    
}


