package ExtJSS.IPas;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.junit.Before;
import org.junit.Test;

public class ExtComboboxTest {
	public String baseurl = "http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout";
	public String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public   WebDriver driver;

  @Before
  public void setup()
  {
	  System.setProperty("webdriver.ie.driver", iedriverpath);
	  driver = new InternetExplorerDriver();
	  driver.get(baseurl);
  }
	
	@Test
	public  void EJCS()  { // Initiate

		//System.out.println("Dynamic id from Jaavscript is "+ getComboBoxId("ccExpireMonth", driver));
	//	String query = "Ext.ComponentQuery.query(\"combobox[name='ccExpireMonth']\");";
	//	System.out.println("Dynamic id from Jaavscript is "+ getComboBoxId("ccExpireMonth", driver));
		String strjquery = "combobox[name='ccExpireMonth']";
		getComboBoxId(strjquery, "July", driver);
	}

	/**
	 * * Click the desired WebElement. * * @param element - expects the
	 * WebElement to be selected.
	 */

	
	
	public void  getComboBoxId(String qry, String itemtoselect, WebDriver drv)
	{
		
		/* String js= "var allcmb = Ext.ComponentQuery.query(\"combobox\");" +
		           "var reqcmb = null;" +
		           " for(var i=0; i < allcmb.length; i++ ) { if (allcmb[i].name == \""+name+"\") { reqcmb = allcmb[i]; break; }} ;" +
		           "return reqcmb.triggerEl.elements[0].id " 
		           
				; */
		String EXTCOMBOBOX = "Ext.ComponentQuery.query(\""+qry+"\")";
	/*	String COMBOBOXTRIGGERID = "var _elem= " + EXTCOMBOBOX + ";" +
		                        " return _elem[0].triggerEl.elements[0].id;" ;*/
		String COMBOBOXTRIGGERID = "var _elem= " + EXTCOMBOBOX + ";" +
                " return _elem[0].id;" ;
		System.out.println("output query script: "+COMBOBOXTRIGGERID);    
		String myid = (String) ((JavascriptExecutor)drv).executeScript(COMBOBOXTRIGGERID);
		System.out.println("Dynamic id from Javascript is "+myid);
	   String js2 =" var cboBox = Ext.ComponentManager.get('"+myid+"');"+
	    "cboBox.expand();"+
	    "cboBox.select(cboBox.findRecordByDisplay('"+itemtoselect+"'));"+
	    "cboBox.collapse();" ;
	   ((JavascriptExecutor)drv).executeScript(js2);
	   
	   
		
	}
}
