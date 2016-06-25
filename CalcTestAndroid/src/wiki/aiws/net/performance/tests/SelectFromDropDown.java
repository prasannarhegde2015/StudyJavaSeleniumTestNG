package wiki.aiws.net.performance.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import wiki.aiws.net.performance.Test;

public class SelectFromDropDown implements Test {
	private String url;
	private String selectIdPrefix;
	private int selectIdSuffixStart;
	private int selectIdSuffixEnd;

	public SelectFromDropDown(String url, String selectIdPrefix,
			int selectIdSuffixStart, int selectIdSuffixEnd) {
		this.url = url;
		this.selectIdPrefix = selectIdPrefix;
		this.selectIdSuffixStart = selectIdSuffixStart;
		this.selectIdSuffixEnd = selectIdSuffixEnd;
	}

	public String getName() {
		return "Select From Dropdown";
	}

	public int execute(WebDriver driver) {
		// Implicit waits time to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);

		for (int i = selectIdSuffixStart; i <= selectIdSuffixEnd; i++) {
			WebElement select = driver.findElement(By.id(selectIdPrefix + i));
			// Select sel = new Select(select);

			select.click();
			WebElement selectValue = driver.findElement(By
					.xpath("//option[@value='" + selectIdPrefix + i
							+ (i % 3 + 1) + "']"));
			selectValue.click();
		}

		return 0;
	}

}
