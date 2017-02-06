package ExtJSS.IPas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ExtComboboxTest {
	public String baseurl = "http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout";
	public String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public String CHROMEdriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public   WebDriver driver;
	public   WebDriverWait wt;

  @Before
  public void setup()
  {
/*	  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);*/
	 /* System.setProperty("webdriver.ie.driver", iedriverpath);
	  driver = new InternetExplorerDriver();*/
			System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
			driver = new ChromeDriver();
	 // driver.get(baseurl);
  }
	
	@Test
	public  void EJCS()  { // Initiate

		//System.out.println("Dynamic id from Jaavscript is "+ getComboBoxId("ccExpireMonth", driver));
	//	String query = "Ext.ComponentQuery.query(\"combobox[name='ccExpireMonth']\");";
	//	System.out.println("Dynamic id from Jaavscript is "+ getComboBoxId("ccExpireMonth", driver));
		driver.get("http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-register");
		driver.findElement(By.name("user")).sendKeys("test");
		driver.findElement(By.id(gettextfieldIDFromFieldLabel("Password",driver))).sendKeys("test");
		driver.findElement(By.id(gettextfieldIDFromFieldLabel("Verify",driver))).sendKeys("test");
		driver.findElement(By.name("first")).sendKeys("test");
		driver.findElement(By.name("last")).sendKeys("test");
		driver.findElement(By.name("company")).sendKeys("test");
		driver.findElement(By.name("email")).sendKeys("test");
		String strjquery = "combobox[fieldLabel='State']";
		SelectComboBoxValuefromExtJS(strjquery, "Florida", driver);
		driver.findElement(By.name("dob")).sendKeys("05/20/16");
		driver.findElement(By.xpath("//span[text()=\"Register\"]"));
	}
	@Test
	public  void EJCS2()  { // Initiate

		//System.out.println("Dynamic id from Jaavscript is "+ getComboBoxId("ccExpireMonth", driver));
	//	String query = "Ext.ComponentQuery.query(\"combobox[name='ccExpireMonth']\");";
	//	System.out.println("Dynamic id from Jaavscript is "+ getComboBoxId("ccExpireMonth", driver));
		String strjquery = "combobox[name='ccExpireMonth']";
		SelectComboBoxValuefromExtJS(strjquery, "July", driver);
	}

	/**
	 * * Click the desired WebElement. * * @param element - expects the
	 * WebElement to be selected.
	 * @throws InterruptedException 
	 */

	
	@Test
	public void Partkeeepr() throws InterruptedException
	{
		wt = new WebDriverWait(driver, 500);
	   driver.get("http://demo.partkeepr.org/");
	   driver.manage().window().maximize();
	   clickElemIfPresent(driver, By.xpath("//div[contains(.,\"Tip of the Day\")]/../following-sibling::*[1]"));
	   driver.findElement(By.id(getExtJSButtonId("OK"))).click();
	 //  driver.findElement(By.id(getExtJSButtonId("Add Part"))).click();
	   clickelem(driver,driver.findElement(By.id(getExtJSButtonId("Add Part"))));
	 /*  fnHighlightMe(driver,driver.findElement(By.id(getExtJSTextFieldID("Name"))) );*/
	   driver.findElement(By.id(getExtJSTextFieldID("Name"))).sendKeys("TestAPI");
	   SelectComboBoxValuefromExtJS("combobox[fieldLabel='Stock User']", "mxf", driver);
	   driver.findElement(By.id(getExtJSTextFieldID("Storage Location"))).sendKeys("BESTELLT");
	   wait(1);
	   wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(.,\"BESTELLT\")]")) );
	   wait(1);
	   wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,\"BESTELLT\")]")));
	   wait(1);
	   clickelem(driver, driver.findElement(By.xpath("//div[contains(.,\"BESTELLT\")]")));
	   wait(1);
	   clickelem(driver, driver.findElement(By.id(getExtJSTextFieldID("Storage Location"))));
	   clickelem(driver, driver.findElement(By.id(getExtJSButtonId("Save"))));
	   wait(4);
	   clickelem(driver, driver.findElement(By.xpath("//div[text()=\"TestAPI\"]")));
	   wait(2);
	   driver.findElement(By.id(getExtJSButtonId("Delete Part"))).click();
	   wait(2);
	   driver.findElement(By.id(getExtJSButtonId("Yes"))).click();
	   wait(5);
	   //Repeat the test for part 2
	   driver.findElement(By.id(getExtJSButtonId("Add Part"))).click();
	   driver.findElement(By.id(getExtJSTextFieldID("Name"))).sendKeys("TestAPI2");
	   SelectComboBoxValuefromExtJS("combobox[fieldLabel='Stock User']", "mxf", driver);
	   driver.findElement(By.id(getExtJSTextFieldID("Storage Location"))).sendKeys("BESTELLT");
	   wait(1);
	   wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(.,\"BESTELLT\")]")) );
	   wait(1);
	   wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,\"BESTELLT\")]")));
	   wait(1);
	   clickelem(driver, driver.findElement(By.xpath("//div[contains(.,\"BESTELLT\")]")));
	   clickelem(driver, driver.findElement(By.id(getExtJSTextFieldID("Storage Location"))));
	   driver.findElement(By.id(getExtJSButtonId("Save"))).click();
	   wait(4);
	//   clickelem(driver, driver.findElement(By.xpath("//div[text()=\"TestAPI2\"]")));
	   SelectRowwithName(driver, "Parts List", "TestAPI2");
	   wait(2);
	   driver.findElement(By.id(getExtJSButtonId("Delete Part"))).click();
	   wait(2);
	   driver.findElement(By.id(getExtJSButtonId("Yes"))).click();
	   
	}
	
	@Test
	public void Partkeeepr2() throws InterruptedException
	{
		InitializePage();
		/*AddPart("TestAPI");
		String strtimestamp = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss").format(new Date());
		AddPart("TestAPI"+strtimestamp);*/
	//	SelectRowwithName(driver, "Parts List", "LM394");
	//	System.out.println("The cell value at rowindex 2  and colindex 5 is :"+getCellValue(driver, "Parts List", "2", "4"));
	//	setCellValue(driver, "Parts List", "2", "7","55 pcs");
		clickelem(driver,driver.findElement(By.id(getExtJSButtonId("Add Part"))));
		clickelem(driver,driver.findElement(By.id(getExtJSTabId("Distributors"))));
		clickelem(driver,driver.findElement(By.id(getExtJSButtonId("Add"))));
	//	setCellValue(driver, "Distributors", "0", "1","UK");
	//	setCellValue(driver, "Distributors", "0", "2","34");
	//	setCellValue(driver, "Distributors", "0", "2","3");
		String cellval = getExtCellValue(driver, "grid[title='Distributors']", 0,2);
		System.out.println("Cell vlaue is : "+ cellval);
		setextCellValue(driver, "grid[title='Distributors']", 0,5 ,"55");
		
		
		JsplitButtonClick(driver, "splitbutton[origText='Claim']");
		
		
	}
	
	@Test
	public void Ext3Demo() throws Exception
	{
		driver.get(baseurl);
		driver.manage().window().maximize();
		WebElement  elm = driver.findElement(By.id(getExtJSRdaioButtonId("American Express")));
		clickelem(driver, elm);
	}
	
	public void  SelectComboBoxValuefromExtJS(String qry, String itemtoselect, WebDriver drv)
	{
		

		String EXTCOMBOBOX = "Ext.ComponentQuery.query(\""+qry+"\")";
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

   public String gettextfieldIDFromFieldLabel(String fieldLabel,WebDriver drv)
   {
	   String TXTFIELD = "Ext.ComponentQuery.query(\""+"textfield[fieldLabel='"+fieldLabel+"']\")";
		/*	String COMBOBOXTRIGGERID = "var _elem= " + EXTCOMBOBOX + ";" +
			                        " return _elem[0].triggerEl.elements[0].id;" ;*/
			String TXTFIELDID = "var _elem= " + TXTFIELD + ";" +
	                " return _elem[0].inputId;" ;
			System.out.println("output query script: "+TXTFIELDID);    
			String myid = (String) ((JavascriptExecutor)drv).executeScript(TXTFIELDID);
			System.out.println("output query script: "+myid);  
			return myid;
   }
   
   public String getExtJSRdaioButtonId(String btntxt)
   {
	   String BTNCNTL = "Ext.ComponentQuery.query(\"radiofield[boxLabel='"+btntxt+"']\");";
	   String BTNTRIGGERID = "var _elem= " + BTNCNTL + ";" +
               " return _elem[0].id;" ;
	   String myid = (String) ((JavascriptExecutor)driver).executeScript(BTNTRIGGERID);
	   return myid;
   }
   
   public String getExtJSButtonId(String btntxt)
   {
	   String BTNCNTL = "Ext.ComponentQuery.query(\"button[text='"+btntxt+"']\");";
	   String BTNTRIGGERID = "var _elem= " + BTNCNTL + ";" +
               " return _elem[0].id;" ;
	   String myid = (String) ((JavascriptExecutor)driver).executeScript(BTNTRIGGERID);
	   return myid;
   }
   
   public String getExtJSTabId(String tabtxt)
   {
	   String BTNCNTL = "Ext.ComponentQuery.query(\"button[text='"+tabtxt+"']\");";
	   String BTNTRIGGERID = "var _elem= " + BTNCNTL + ";" +
               " return _elem[0].id;" ;
	   String myid = (String) ((JavascriptExecutor)driver).executeScript(BTNTRIGGERID);
	   return myid;
   }
   
   public String getExtJSTextFieldID(String lbltxt)
   {
	   String BTNCNTL = "Ext.ComponentQuery.query(\"textfield[fieldLabel='"+lbltxt+"']\");";
	   String BTNTRIGGERID = "var _elem= " + BTNCNTL + ";" +
               " return _elem[0].inputId;" ;
	   String myid = (String) ((JavascriptExecutor)driver).executeScript(BTNTRIGGERID);
	   return myid;
   }
   
   
   public void InitializePage()
   {
	   wt = new WebDriverWait(driver, 500);
	   driver.get("http://demo.partkeepr.org/");
	   driver.manage().window().maximize();
	   clickElemIfPresent(driver, By.xpath("//div[contains(.,\"Tip of the Day\")]/../following-sibling::*[1]"));
	   driver.findElement(By.id(getExtJSButtonId("OK"))).click();
   }
   public void wait(int secs) throws InterruptedException
   {
	   Thread.sleep(secs*1000);
   }
   
   public  void fnHighlightMe(WebDriver driver,WebElement element) throws InterruptedException{
	   //Creating JavaScriptExecuter Interface
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    for (int iCnt = 0; iCnt < 3; iCnt++) {
	       //Execute javascript
	          js.executeScript("arguments[0].style.border='4px groove green'", element);
	          Thread.sleep(1000);
	          js.executeScript("arguments[0].style.border=''", element);
	          
	    }
	  }
   
   public void clickElemIfPresent(WebDriver dr, By by)
   
   {
	   try 
	   {
		   wait(3);
		   WebDriverWait  wtins = new WebDriverWait(dr, 7);
		   wtins.until(ExpectedConditions.presenceOfElementLocated(by));
		   WebElement elem = dr.findElement(by);
		   elem.click();
		   
	   }
	   catch(NoSuchElementException e)
	   {
		   System.out.println("No Such Elememnt Present..igonre");
	   }
	   catch(StaleElementReferenceException e)
	   {
		   System.out.println("Stale Elemement Exception : ..igonre");
	   }
	   catch(Exception e)
	   {
		   System.out.println("Other Exception ....ignore "+e.getMessage());
	   }
   }
   
   public void clickelem(WebDriver dr, WebElement el)
	{
		Actions  acn = new Actions(dr);
		/*acn.click(el).build().perform();*/
		acn.moveToElement(el).click().perform();
	}
   
   public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
   }
   
   
   public void AddPart(String partName) throws InterruptedException
   {
	   
	   clickelem(driver,driver.findElement(By.id(getExtJSButtonId("Add Part"))));
	 /*  fnHighlightMe(driver,driver.findElement(By.id(getExtJSTextFieldID("Name"))) );*/
	   driver.findElement(By.id(getExtJSTextFieldID("Name"))).sendKeys(partName);
	   SelectComboBoxValuefromExtJS("combobox[fieldLabel='Stock User']", "mxf", driver);
	   driver.findElement(By.id(getExtJSTextFieldID("Storage Location"))).sendKeys("BESTELLT");
	   wait(1);
	   wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(.,\"BESTELLT\")]")) );
	   wait(1);
	   wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,\"BESTELLT\")]")));
	   wait(1);
	   clickelem(driver, driver.findElement(By.xpath("//div[contains(.,\"BESTELLT\")]")));
	   wait(1);
	   clickelem(driver, driver.findElement(By.id(getExtJSTextFieldID("Storage Location"))));
	   clickelem(driver, driver.findElement(By.id(getExtJSButtonId("Save"))));
	   wait(4);
	   clickelem(driver, driver.findElement(By.xpath("//div[text()=\""+partName+"\"]")));
	   wait(2);
	   driver.findElement(By.id(getExtJSButtonId("Delete Part"))).click();
	   wait(2);
	   driver.findElement(By.id(getExtJSButtonId("Yes"))).click();
	   wait(5);
   }
   
   public void SelectRowwithName( WebDriver  idrv, String gridtittle , String rowName)
   {
	   String jsRowCount = "var elem = Ext.ComponentQuery.query(\"grid[title='"+gridtittle+"']\");"+
               "return elem[0].store.data.items.length" ;
	   Long gridrowcount  = (Long) ((JavascriptExecutor)idrv).executeScript(jsRowCount);
	   System.out.println("Rows count in the grid is "+gridrowcount);
	   
	   
	   for ( int i=0; i <  gridrowcount; i++)
	   {
       String js = "var elem = Ext.ComponentQuery.query(\"grid[title='"+gridtittle+"']\");"+
                   "return elem[0].store.data.items["+i+"].data.name" ;
       String js2 = "";
    
       String cellvalue  = (String) ((JavascriptExecutor)idrv).executeScript(js);
       System.out.println("Row Name is  "+cellvalue);
       if  (cellvalue.equals(rowName))
       {
               js2 = " var grid = Ext.ComponentManager.get(Ext.ComponentQuery.query(\"grid[title='"+gridtittle+"']\")[0].id);" +
                           "grid.selModel.select("+i+");" ;
               ((JavascriptExecutor)idrv).executeScript(js2);
               break;
       }
	   }
       
    		// elem[0].store.data.items[3].data.name
   }
   
   
   public void jcellactiondblclick1(WebDriver drv, String gridparttitle, String strow, String stcol)
   {

      String scripvcolcount = "var  gridcoll = Ext.ComponentQuery.query(\"grid[title*='"+gridparttitle+"']\");"+
      
      	" return Ext.ComponentManager.get(gridcoll[0].id).columns.length";
      String vcolcount = (String)((JavascriptExecutor)drv).executeScript(scripvcolcount);
      System.out.println("The column count value "+vcolcount);
      System.out.println("Executing JavaScript using ext comp query   ");
      String strscript = "var  gridcoll = Ext.ComponentQuery.query(\"grid[title*='"+gridparttitle+"']\");"+
      	"var targetcell = gridcoll[0].el.dom.childNodes[3].childNodes[0].childNodes[0].childNodes["+( Integer.parseInt(vcolcount) - 1)+"].childNodes["+Integer.parseInt(strow)+"].cells["+Integer.parseInt(stcol)+" ].childNodes[0];"+
				  "var clickEvent  = document.createEvent ('MouseEvents');"+
				  "clickEvent.initEvent ('dblclick', true, true);" +
				  "targetcell.dispatchEvent (clickEvent);";
      ((JavascriptExecutor)drv).executeScript (strscript);
   }
   
   
   public String getCellValue(WebDriver drv, String gridparttitle,  String rowindex , String colindex)
   {
	   System.out.println("Row Number :"+(Integer.parseInt(rowindex)));
	   System.out.println("Column  Number :"+(Integer.parseInt(colindex)));
	   String cellevalscript = "var gridid = Ext.ComponentQuery.query(\"grid[title*='"+gridparttitle+"']\")[0].id;"+
	                           "var grid = Ext.ComponentManager.get(gridid);" +
                              "var record = grid.getStore().getAt("+(Integer.parseInt(rowindex))+");" +
                             //  "var record = grid.getStore().getAt(2);" +
                               "var celldata = record.get(grid.getView().getHeaderCt().getVisibleGridColumns()["+
                             (Integer.parseInt(colindex))+"].dataIndex);"+
                               "return celldata;";
	  // System.out.println("The cell value at "+rowindex+"  and colindex "+colindex+" is :"+cellevalscript);
	   String cellvalue = (String)((JavascriptExecutor)drv).executeScript (cellevalscript);
	   return cellvalue;
   }
   
   public void setCellValue(WebDriver drv, String gridparttitle,  String rowindex , String colindex , String strvalue)
   {
	   System.out.println("Row Number :"+(Integer.parseInt(rowindex)));
	   System.out.println("Column  Number :"+(Integer.parseInt(colindex)));
	   String cellevalscript = "var gridid = Ext.ComponentQuery.query(\"grid[title*='"+gridparttitle+"']\")[0].id;"+
	                           "var grid = Ext.ComponentManager.get(gridid);" +
                              "var record = grid.getStore().getAt("+(Integer.parseInt(rowindex))+");" +
                               "record.set(grid.getView().getHeaderCt().getVisibleGridColumns()["+
                              (Integer.parseInt(colindex))+"].dataIndex,'"+strvalue+"');";
	   ((JavascriptExecutor)drv).executeScript(cellevalscript);
	  
   }
   
   public String getExtCellValue(WebDriver drv, String qry,  int rowindex , int colindex)
   {
	   System.out.println("Row Number :"+(rowindex));
	   System.out.println("Column  Number :"+(colindex));
	   String compquery = "Ext.ComponentQuery.query(\""+qry+"\")";
		String strgridid = "var grid= " + compquery + ";" +
               " return grid[0].id;" ;
		 
		String gridid = (String)((JavascriptExecutor)drv).executeScript (strgridid);
		System.out.println("Grid Id  :"+gridid);
	   String cellevalscript =  "var gridid = '"+ gridid +"';" +
			   					"var grid = Ext.ComponentManager.get(gridid);" +
			   					"var record = grid.getStore().getAt("+(rowindex)+");" +
			   					"var celldata = record.get(grid.getView().getHeaderCt().getVisibleGridColumns()["+
                             (colindex)+"].dataIndex);"+
                               "return celldata;";
	   String cellvalue = ((JavascriptExecutor)drv).executeScript(cellevalscript).toString();
	   return cellvalue;
   }
   
   public void setextCellValue(WebDriver drv, String qry,  int rowindex , int colindex , String strvalue)
   {
	   System.out.println("Row Number :"+(rowindex));
	   System.out.println("Column  Number :"+(colindex));
	   System.out.println("Row Number :"+(rowindex));
	   System.out.println("Column  Number :"+((colindex)));
	   String compquery = "Ext.ComponentQuery.query(\""+qry+"\")";
		String strgridid = "var grid= " + compquery + ";" +
               " return grid[0].id;" ;
		String gridid = (String)((JavascriptExecutor)drv).executeScript(strgridid);
		System.out.println("Grid Id  :"+gridid);
	   String cellevalscript = "var gridid = '"+ gridid +"';" +
			   				   "var grid = Ext.ComponentManager.get(gridid);" +
                               "var record = grid.getStore().getAt("+(rowindex)+");" +
                               "record.set(grid.getView().getHeaderCt().getVisibleGridColumns()["+
                              (colindex)+"].dataIndex,'"+strvalue+"');";
	   ((JavascriptExecutor)drv).executeScript(cellevalscript);
	  
   }
   
   public void JsplitButtonClick(WebDriver drv, String qry)
   {

	   	String compquery = "Ext.ComponentQuery.query(\""+qry+"\")";
		String strgridid = "var elemid= " + compquery + ";" +
						   " return elemid[0].id;" ;
		String elemid = (String)((JavascriptExecutor)drv).executeScript(strgridid);
		System.out.println("Elem Id  :"+elemid);
	   String cellevalscript = "var elem = Ext.ComponentManager.get(elemid);" +
			                   "elem.showMenu();" ;                        
	   ((JavascriptExecutor)drv).executeScript(cellevalscript);
	  
   }
}