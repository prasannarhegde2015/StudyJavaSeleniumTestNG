package ExtJSS.IPas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class EmpowerLogin {
	
	public String baseurl = "";
	public String iedriverpath = "";
	public WebDriver drv ;
	
	@Before
	public void setup()
	{
		System.setProperty("webdriver.ie.driver", iedriverpath);
		drv = new InternetExplorerDriver();
		drv.get(baseurl);
	}
	@Test
	public void login()
	{
		
	}
	@After
	public void teardown()
	{
		
	}

}
