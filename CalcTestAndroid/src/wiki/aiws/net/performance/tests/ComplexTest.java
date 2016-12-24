package wiki.aiws.net.performance.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wiki.aiws.net.performance.Test;

public class ComplexTest implements Test {
	private String url;

	public ComplexTest(String url) {
		this.url = url;
	}

	public String getName() {
		return "Complex Test";
	}

	public int execute(WebDriver driver) {
		// Implicit waits time to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(url);

		// login
		driver.findElement(By.id("j_username")).sendKeys("user");
		driver.findElement(By.id("j_password")).sendKeys("user");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// Enter information
		driver.findElement(By.id("firstName")).sendKeys("Cam");
		driver.findElement(By.id("lastName")).sendKeys("Hoang");

		// Enter birthday
		driver.findElement(By.id("birthday")).click();
		boolean selected = false;
		while (!selected) {
			String month = driver.findElement(
					By.className("ui-datepicker-month")).getText();
			String year = driver
					.findElement(By.className("ui-datepicker-year")).getText();
			if (month.equals("September") && year.equals("2012")) {
				driver.findElement(By.xpath("//a[text()='15']")).click();
				selected = true;
			} else {
				driver.findElement(By.xpath("//a[@title='Prev']")).click();
			}
		}

		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.id("ui-datepicker-div")));

		driver.findElement(By.xpath("//input[@type='radio' and @value='male']"))
				.click();
		driver.findElement(By.id("education")).click();
		driver.findElement(By.xpath("//option[@value='Master']")).click();
		driver.findElement(By.name("englishSpeaker")).click();

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// Click logout
		driver.findElement(By.id("logout")).click();
		driver.findElement(By.id("j_username"));
		return 0;
	}

}