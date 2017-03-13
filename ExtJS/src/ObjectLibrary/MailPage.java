package ObjectLibrary;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonFunctions.Helper;

public class MailPage {
	Objectdef obj = new Objectdef();
	public static String ScreenshotDirectory = "E:\\Prasanna\\JavaTutorial\\Screenshots\\Screenshots";

	public void clickCompose(WebDriver drv) {

		// Helper hlp = new Helper();
		obj.setName("MailPage.Properties");
		WebDriverWait wait;
		wait = new WebDriverWait(drv, 500);
		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(drv.findElement(obj.getBy("frmcompose"))));
		// drv.switchTo().frame(drv.findElement(obj.getBy("frmcompose")));
		wait.until(ExpectedConditions.presenceOfElementLocated(obj
				.getBy("btncompose")));
		drv.findElement(obj.getBy("btncompose")).click();

	}

	public void sendemail(WebDriver drv, String to, String sub, String content)
			throws InterruptedException {
		WebDriverWait wait;
		wait = new WebDriverWait(drv, 500);
		wait.until(ExpectedConditions.presenceOfElementLocated(obj
				.getBy("divcontent")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drv.findElement(obj.getBy("divcontent")).click();
		drv.findElement(obj.getBy("divto")).click();
		drv.findElement(obj.getBy("txtto")).click();
		drv.findElement(obj.getBy("txtto")).sendKeys(to);
		Thread.sleep(1000);
		drv.findElement(obj.getBy("divcontent")).click();
		// drv.findElement(obj.getBy("txtsub")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drv.findElement(obj.getBy("txtsub")).sendKeys(sub);
		drv.findElement(obj.getBy("divcontent")).click();
		drv.findElement(obj.getBy("divcontent")).sendKeys(content);
		drv.findElement(obj.getBy("btnsend")).click();
	}

	public void clickprimarytab(WebDriver drv) {
		WebDriverWait wait;
		wait = new WebDriverWait(drv, 500);
		drv.findElement(obj.getBy("lnkinbox")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(obj
				.getBy("tabprimary")));
		drv.findElement(obj.getBy("tabprimary")).click();
	}

	public void clickemailwithsubjectline(WebDriver drv, String subjecttext) {
		WebDriverWait wait;
		wait = new WebDriverWait(drv, 500);
		wait.until(ExpectedConditions.presenceOfElementLocated(obj
				.getBy("linkmymail")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> mathces = drv.findElements(obj.getBy("linkmymail"));
		for (WebElement elem : mathces) {
			try {
				if (elem.isDisplayed()) {
					elem.click();
					break;
				}
			} catch (Exception e) {
				System.out.println("Got Exception " + e);
			}
		}

	}

	public void VerifyEmailText(WebDriver drv, String subjecttext) {
		Helper hlp = new Helper();
		WebDriverWait wait;
		wait = new WebDriverWait(drv, 500);
		wait.until(ExpectedConditions.presenceOfElementLocated(obj
				.getBy("emailbodytext")));
		String acttext = drv.findElement(obj.getBy("emailbodytext")).getText();
		if (acttext.equalsIgnoreCase(subjecttext)) {
			System.out
					.println("*************Test Pass:   for Email found******** Expected "
							+ subjecttext + " Actual " + acttext);
		} else {
			System.out
					.println("*************Test Fail:   for Email found******** Expected "
							+ subjecttext + " Actual " + acttext);
		}
		String timestampfilename = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss")
				.format(new Date());

		try {
			hlp.CaptureScreesnhot(drv, ScreenshotDirectory + "\\Image_"
					+ timestampfilename + ".jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletemail(WebDriver drv) throws InterruptedException,
			AWTException {

		clickdel(drv);
		// drv.findElement(obj.getBy("btndelete")).click();
	}

	public void logout(WebDriver drv) {
		WebDriverWait wait;
		wait = new WebDriverWait(drv, 500);

		drv.findElement(obj.getBy("btnlogout")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(obj
				.getBy("btnsignout")));
		drv.findElement(obj.getBy("btnsignout")).click();

	}

	public void clickdel(WebDriver driver) throws InterruptedException {
		List<WebElement> elements = driver.findElements(obj.getBy("tabstrip"));
		int i = 0;
		for (WebElement element : elements) {
			i++;
			// String val =element.getAttribute("data-tooltip");
			// System.out.println("got button "+i);
			if (element.isDisplayed()) {
				String val = "";
				String tagname = "";
				// System.out.println("Clicking Button not hiddden "+i);
				try {
					val = element.getAttribute("title");
					tagname = element.getTagName();
					// System.out.println("No exepction, Tag anme "+val+" ...tag "+tagname);
				} catch (Exception e) {
					System.out
							.println("Element does not have attri as data-tooltip "
									+ e);
				}
				if (val != null) {
					if (val.equals("Delete")) {
						element.click();
						Thread.sleep(2000);
						break;
					}
				}
			}
		}
	}

}
