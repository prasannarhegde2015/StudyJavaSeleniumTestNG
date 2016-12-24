package wiki.aiws.net.performance.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import wiki.aiws.net.performance.Test;

public abstract class AbstractLocatingElement implements Test {

	protected String url;
	protected int elementSuffixStart;
	protected int elementSuffixEnd;

	public AbstractLocatingElement(String url, int elementSuffixStart,
			int elementSuffixEnd) {
		this.url = url;
		this.elementSuffixStart = elementSuffixStart;
		this.elementSuffixEnd = elementSuffixEnd;
	}

	public abstract By getLocator(int index);

	public int execute(WebDriver driver) {
		// Implicit waits time to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		driver.get(url);
		for (int i = elementSuffixStart; i <= elementSuffixEnd; i++) {
			driver.findElement(getLocator(i));
		}

		return 0;
	}

}