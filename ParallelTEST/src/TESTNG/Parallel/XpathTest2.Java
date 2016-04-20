package mypocFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test05 {

	@Test
	public void extjstest() {

		FirefoxDriver drv = new FirefoxDriver();
		drv.manage().window().maximize();
		drv.get("http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout");
		WebDriverWait wait = new WebDriverWait(drv, 500);
		// xp1 = //span[text()="State"]/../following-sibling::*[1]/*[2]/*[2]
		// xp2 =
		// //span[text()="Expiration"]/../following-sibling::*[1]//*[2]/*[2]/*[2]
		String searchByValue = "//span[text()=\"Expiration\"]/../following-sibling::*[1]//*[2]/*[2]/*[2]";
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(searchByValue)));
		try {
			drv.findElement(By.xpath(searchByValue)).click();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}

		List<WebElement> allwebelem = drv.findElementsByTagName("li");
		for (WebElement el : allwebelem) {
			if (el.getText().equalsIgnoreCase("April") && el.isDisplayed()) {
				el.click();
				break;
			}
		}
		// TODO Auto-generated method stub

	}

}
