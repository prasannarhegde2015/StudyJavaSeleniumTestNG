package com.leanft.testeclispe;

import java.net.URI;

import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.XPathDescription;

public class TestHoneLink {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
		config.setServerAddress(new URI("ws://localhost:5095"));
		SDK.init(config);
		Reporter.init();

		// test code
		Browser browser = BrowserFactory.launch(BrowserType.CHROME);
		browser.navigate("http://newtours.demoaut.com");
		browser.describe(EditField.class,
				new XPathDescription("//input[@name='userName']")).setValue(
				"test");
		browser.describe(EditField.class,
				new XPathDescription("//input[@name='password']")).setValue(
				"test");
		// browser.describe(Image.class,
		// new XPathDescription("//input[@name='login']")).click();
		browser.close();

		Reporter.generateReport();
		SDK.cleanup();
	}

}
