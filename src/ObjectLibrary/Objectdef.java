package ObjectLibrary;

import org.openqa.selenium.By;

import CommonFunctions.Helper;

public class Objectdef {
	Helper hlp = new Helper();
	 private String iname;

	    public String getName() { return this.iname; }
	    public void setName(String name) { this.iname = name; }
	public By getBy(String propertyname)
	{
		try
		{
	    hlp.setName(this.iname);
		String  propvalue = hlp.readpropertiesfileall().getProperty(propertyname);
	  //  System.out.println("PropertyLocator  :"+propvalue);
		String[] propvlauelocator = propvalue.split(":");
		String locator = propvlauelocator[0];
		String locatorvalue = "";
		int sepindex = propvalue.indexOf(":");
		locator = propvalue.substring(0, sepindex);
		locatorvalue=propvalue.substring(sepindex+1,propvalue.length());
	//	System.out.println("Locator  :"+locator);
	//	System.out.println("LocatorValue  :"+locatorvalue);
		
		switch (locator.toLowerCase())
		{
		case "id" : 
			{
				//System.out.println("locator value :"+locatorvalue);
				return By.id(locatorvalue); 
			}
		case "name" : 
		{ 
			return By.name(locatorvalue);
		}
		case "cssselector" : 
		{
			//System.out.println("locator value :"+locatorvalue);
			return By.cssSelector(locatorvalue);
		  
		}
		case "xpath" : 
		{
			return By.xpath(locatorvalue);
		 
		}
		default: {
			return null;
			
		}
		}
		}
		catch(Exception ex)
		{
			System.out.println("Error inside getBy :"+ex.toString());
			return null;
		}
	}
	
	public String getval(String propertyname)
	{
		String retval = "";
		try
		{
			hlp.setName(this.iname);
		   retval = hlp.readpropertiesfileall().getProperty(propertyname);
		return retval;
		}
		catch(Exception e)
		{
			return retval;
		}
	}

}
