package mypocFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test04 {

	@Test
	public void extjstest() {

		FirefoxDriver drv = new FirefoxDriver();
		drv.get("http://dev.sencha.com/deploy/ext-4.0.1/examples/form/combos.html");
		WebDriverWait wait = new WebDriverWait(drv, 500);
		String searchByValue = "//label[text()= \"Select a single state:\"]/following-sibling::*[1]/*[2]/*[1]";
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(searchByValue)));
		drv.findElement(By.xpath(searchByValue)).click();

		List<WebElement> allwebelem = drv.findElementsByTagName("li");
		for (WebElement el : allwebelem) {
			if (el.getText().equalsIgnoreCase("Colorado")) {
				el.click();
				break;
			}
		}
		// TODO Auto-generated method stub

	}

}
