package com.nor.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.nor.testfrmk.Context;
import com.nor.testfrmk.Global;

public class DriverUtils {
	public  String CHROMEdriverpath = "";
	public  String iedriverpath = "";
	public  WebDriver globalDriver = null;
	public  String baseurl = "";
	public void InitializeDriver()
	{
		    System.out.println("Starting Browser ...");
		    Global.gethelperutils().setName("Configuration.Properties");
		    Helper hlp = Global.gethelperutils();
		    hlp.setName("Configuration.Properties");
		    String browser = hlp.readpropertiesfileall().getProperty("browser");
		    
		    switch (browser.toLowerCase()) {
			case "ie":
			{
				iedriverpath = hlp.readpropertiesfileall().getProperty("iedriverpath");
				baseurl= hlp.readpropertiesfileall().getProperty("baseurl");
				System.setProperty("webdriver.ie.driver", iedriverpath);
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				globalDriver= new InternetExplorerDriver(cap);
				globalDriver.manage().window().maximize();
				globalDriver.get(baseurl);
				Context.setDriver(globalDriver);
			//	context.setDriver(globalDriver);
				break;
			}
			case "chrome":
			{
				CHROMEdriverpath = hlp.readpropertiesfileall().getProperty("chromedriverpath");
				baseurl= hlp.readpropertiesfileall().getProperty("baseurl");
				System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("chrome.switches","--disable-extensions");
				options.addArguments("chrome.switches","--disable-infobars");
				Map<String, Object> prefs = new HashMap<String, Object>();
	    			prefs.put("credentials_enable_service", false);
	    			prefs.put("profile.password_manager_enabled", false);
	    			options.setExperimentalOption("prefs", prefs);
				globalDriver= new ChromeDriver(options);
				globalDriver.manage().window().maximize();
				globalDriver.get(baseurl);
				Context.setDriver(globalDriver);
			//	context.setDriver(globalDriver);
				break;
			}
			case "firefox":
			{
				//Set all desired Firefox profiles using profiles Ini
				break;
			}
			default:
				break;
			}
			
			
		
	}

	
	public WebDriver getDriver()
	
	{
		if (globalDriver == null)
		{
			InitializeDriver();
			
		}
		return globalDriver;
	}
   
	
}
