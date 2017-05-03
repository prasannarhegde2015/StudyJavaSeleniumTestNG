package com.nordea.framework;

import com.nordea.utils.DriverUtils;
import com.nordea.utils.Helper;
import com.nordea.utils.SeleniumUtils;
import com.nordea.utils.WinDriverUtils;

public class Global {
	public static Helper hlpinst;

	public static DriverUtils getdriverutils() {
		System.out.println("Inside Global");
		return new DriverUtils();
	}

	public static WinDriverUtils getwindriverutils() {
		System.out.println("Inside WinDriver utils");
		return new WinDriverUtils();
	}

	public static Helper gethelperutils() {
		System.out.println("Inside Helper");
		if (hlpinst == null) {
			System.out.println("Creating a new Helper instance ");
			hlpinst = new Helper();
		} else {
			System.out.println("Not creting a new isntnace as Helper instance Existed");
		}
		return hlpinst;
	}

	public static SeleniumUtils getSeleniumUtils() {
		System.out.println("Inside Selenium Utils");
		return new SeleniumUtils();
	}

}
