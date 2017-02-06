package com.testalllinks;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllLinks {
	public String baseur = "https://powaiadpass.lntinfotech.com/showLogin.cc";
	public String iedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
	public String chromedriverpath = "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;
	public WebDriverWait wait;
	public String logfilepath = "c:\\temp\\out.csv";
	public String colnames = "SrNo;WebElemntType;Text;Hieght;Width";

	enum attr {
		type, value, href, id, name
	};

	@Before
	public void setup() {
		
		  System.setProperty("webdriver.ie.driver", iedriverpath); 
		  driver = new InternetExplorerDriver();
		 

		/*System.setProperty("webdriver.chrome.driver", chromedriverpath);
		driver = new ChromeDriver();*/

		/* driver = new FirefoxDriver(); */
		driver.get(baseur);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 500);
	}

	@Test
	public void test() throws Exception {

		/*
		 * WebElement mvelem = wait.until(ExpectedConditions
		 * .presenceOfElementLocated(By.id("Menu1")));
		 * System.out.println(mvelem.getText()); Mousemove(driver, mvelem);
		 */
		List<WebElement> allwebelems = driver.findElements(By.xpath("//*"));
		HelperUtils hlp = new HelperUtils();

		System.out.println("All Web Elements count is: " + allwebelems.size());
		int cnt = 1;
		for (WebElement indelem : allwebelems) {
			String[] arrdetails = getElementType(indelem).split(":");
			String elemtext = "";
			String elemtype = arrdetails[0];
			if (arrdetails.length == 2) {
				elemtext = arrdetails[1];
			}
			hlp.createCSVFile(
					logfilepath,
					colnames,
					Integer.toString(cnt) + ";" + elemtype + ";" + elemtext
							+ ";" + indelem.getLocation().getX()+ ";"
							+ indelem.getLocation().getY());
			cnt++;
		}

	}

	/*
	 * @Test public void test2() { WebElement mvelem =
	 * driver.findElement(By.id("Menu1")); System.out.println(mvelem.getText());
	 * Mousemove(driver, mvelem);
	 */

	// }

	public String getElementType(WebElement el) {
		String elemtag = el.getTagName();
		String elemdetails = "";

		switch (elemtag) {
		case "a":
			elemdetails = "Hyperlink:" + el.getText();
			break;
		case "input":
			String elattr = "";
			switch (elattr) {
			case "type":
				String atval = el.getAttribute("type");
				if (atval.equalsIgnoreCase("text")) {
					elemdetails = "TextBox";
				}
				break;
			case "href":

				break;
			case "id":

				break;
			case "value":

				break;
			default:
				elemdetails = " Element Type Unknonwn Link Text:";

				break;
			}

		case "div":
			elemdetails = "Div";
			break;
		case "span":
			elemdetails = "Span";
			break;
		case "table":
			elemdetails = " Table:" + el.getText();
			break;
		case "img":
			elemdetails = "Image:" + el.getText();
			break;

		default:
			elemdetails = "Unkonwn:" + elemtag;
			break;
		}
		return elemdetails;
	}

	public void ClickElement(WebDriver drv, WebElement elem) {
		Actions act = new Actions(drv);
		act.click(elem);
		act.perform();
	}

	public void deluser(WebDriver dd, String fnn) {

		List<WebElement> tbs = dd.findElements(By.name("firstname"));
		tbs.get(0).clear();
		tbs.get(0).sendKeys(fnn);
		driver.findElement(By.name("btndelete")).click();

	}

	public void Mousemove(WebDriver drv, WebElement elem) {
		Actions act = new Actions(drv);
		act.moveToElement(elem).build().perform();
		sleep(10);

		// act.perform();
	}

	public boolean isonscreen(WebDriver dr, WebElement el) {
		boolean isonscreen = true;
		try {
			int xpos = el.getLocation().getX();
			int ypos = el.getLocation().getY();
			System.out.println("X position :" + xpos);
			System.out.println("Y position :" + ypos);
		} catch (Exception ex) {
			isonscreen = false;
		}
		return isonscreen;
	}

	@After
	public void teardown() {

		// driver.close();
		// driver.quit();

	}

	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {

		}
	}
}
