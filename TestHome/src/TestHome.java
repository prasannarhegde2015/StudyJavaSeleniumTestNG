


import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
public class TestHome {
	public static void main(String []args){
		File file = new File("E:/Prasanna/JavaTutorial/Selenium/chromedriver_win_23.0.1240.0/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeDriver idriver = new ChromeDriver();
		idriver.navigate().to("http://home.gopal.com");
        System.out.println("trying to Click Link --.[ php samples link]");
        WebElement elem = ( WebElement)idriver.findElement(By.linkText("PHP Samples"));
        System.out.println("element detected " + elem.getText().toString() + " @ location: ");
        elem.click();
	}

}
