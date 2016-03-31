package com.example.tests;

import com.thoughtworks.selenium.*;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import java.util.regex.Pattern;

//*NEW _ ADDED THIS IMPORT

import org.openqa.selenium.*;

//import org.openqa.selenium.ie.*;

//import org.openqa.selenium.firefox.*;

import org.openqa.selenium.chrome.*;

@SuppressWarnings({ "deprecation", "unused" })
public class Selenium2Test extends SeleneseTestCase {

	@Before

	public void setUp() throws Exception {

//*NEW - Commented below line and added the remaining 2 lines.		

//selenium = new DefaultSelenium

//("localhost", 4444, "*chrome", "http://www.bing.com/");

//		WebDriver driver = new InternetExplorerDriver();



		WebDriver driver = new  ChromeDriver();

		selenium = new WebDriverBackedSelenium

                         (driver, "http://www.bing.com");

//comenting below implies taht the default selenium rc server at port 4444 is running

//else if you uncommment it - then it will automatically launch selenium rc server.

//		selenium.start();

	}




	@Test

	public void testUntitled2() throws Exception {

		selenium.open("/");

		selenium.type("sb_form_q", "selenium tutorial");

		selenium.click("sb_form_go");

		selenium.waitForPageToLoad("80000");

		selenium.click("link=www.jroller.com");

		selenium.waitForPageToLoad("80000");

	}

	@After

	public void tearDown() throws Exception {

		selenium.stop();

	}

}
