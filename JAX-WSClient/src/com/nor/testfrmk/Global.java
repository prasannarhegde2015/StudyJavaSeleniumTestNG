package com.nor.testfrmk;

import com.nor.utils.DriverUtils;
import com.nor.utils.Helper;
import com.nor.utils.SeleniumUtils;

public class Global {
	public static Helper hlpinst;
	
	public static DriverUtils getdriverutils()
	{
		System.out.println("Inside Global");
		return new DriverUtils();
	}
	
	public static Helper gethelperutils()
	{
		System.out.println("Inside Helper");
		if (hlpinst == null )
		{
			System.out.println("Creating a new Helper instance ");
			hlpinst = new Helper();
		}
		else
		{
			System.out.println("Not creting a new isntnace as Helper instance Existed");
		}
		return hlpinst;
	}

	public static SeleniumUtils getSeleniumUtils()
	{
		System.out.println("Inside Selenium Utils");
		return new SeleniumUtils();
	}
	 
}
