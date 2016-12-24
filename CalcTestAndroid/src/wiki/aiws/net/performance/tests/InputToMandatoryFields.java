package wiki.aiws.net.performance.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import wiki.aiws.net.performance.Test;

public class InputToMandatoryFields implements Test {
	private String url;
	private String inputIdPrefix;
	private int inputIdSuffixStart;
	private int inputIdSuffixEnd;

	public InputToMandatoryFields(String url, String inputIdPrefix,
			int inputIdSuffixStart, int inputIdSuffixEnd) {
		this.url = url;
		this.inputIdPrefix = inputIdPrefix;
		this.inputIdSuffixStart = inputIdSuffixStart;
		this.inputIdSuffixEnd = inputIdSuffixEnd;
	}

	public String getName() {
		return "Input To Mandatory Fields";
	}

	public int execute(WebDriver driver) {
		// Implicit waits time to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(url);
		for (int i = inputIdSuffixStart; i <= inputIdSuffixEnd; i++) {
			// WebElement label = driver.findElement(By.xpath("//label[@for='"
			// + inputIdPrefix + i + "']"));
			// if (i % 2 == 0) {
			// if (!label.getText().trim().endsWith("*:")) {
			// throw new IllegalStateException("Invalid sample page.");
			// }
			WebElement input = driver.findElement(By.id(inputIdPrefix + i));
			// input.clear();
			// .sendKeys("Prasanna" + i);
			JavascriptSetValue(driver, input, "Prasanna" + i);
			// }
		}

		return 0;
	}

	public void JavascriptSetValue(WebDriver drv, WebElement el, String val)

	{
		((JavascriptExecutor) drv).executeScript("arguments[0].value='" + val
				+ "'", el);
	}

}
