package TESTNG.Parallel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
 
public class TestBase {
 
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;
    WebDriver drv = null;
    Object obj = new Object();
    StringBuilder  sb = new StringBuilder();
    int cntr = 1;
    @BeforeMethod
    @Parameters("execremote")
    public void setUp(String execremote) throws MalformedURLException {
    	threadDriver = new ThreadLocal<RemoteWebDriver>();
  if  ( execremote.equalsIgnoreCase("yes" ))
  {
        
        DesiredCapabilities dc = new DesiredCapabilities();
        FirefoxProfile fp = new FirefoxProfile();
        dc.setCapability(FirefoxDriver.PROFILE, fp);
        dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        threadDriver.set(new RemoteWebDriver(new URL("http://10.136.176.117:5566/wd/hub"), dc));
  }
  else
  {
	  // run local driver webdriver
	  drv = new FirefoxDriver();
	  
 
  }
    }
 
    @Parameters("execremote")
    public WebDriver getDriver(String execremote) {
    	if  ( execremote.equalsIgnoreCase("yes") )
    	  {
        return threadDriver.get();
    	  }
    	else
    	{
    	  return drv;
    	}
    }
 
    @AfterMethod
    @Parameters("execremote")
    public void closeBrowser(String execremote) {
        getDriver(execremote).quit();
        
 
    }
    @AfterSuite
    public void postsuite() throws IOException
    {
    	obj.genHTMLTABLE();
    }

       
	
}