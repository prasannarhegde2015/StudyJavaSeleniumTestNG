package wiki.aiws.net.performance;

import org.openqa.selenium.WebDriver;

public interface Test {
	String getName();

	int execute(WebDriver driver);
}
