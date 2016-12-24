import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
/*import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;*/

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class CheesecakeFactory {
 
	FirefoxDriver driver;
 
    @Before
    public void startDriver() {
       driver = new FirefoxDriver();
    }
 
    @After
    public void stopDriver() {
        driver.close();
    }
 
    @Test
    public void testlistCheesecakes() {
        driver.get("http://www.thecheesecakefactory.com/");
        try {
			driver.manage().timeouts().implicitlyWait(3,TimeUnit.MINUTES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.linkText("Menu")).click();
        driver.findElement(By.linkText("Cheesecake")).click();
        List<WebElement> cheesecakes = driver.findElements(By.xpath("id('leftNav_levelTwo')//li"));
 
        System.out.println(cheesecakes.size() + " cheesecakes:");
        for (int i=0; i<cheesecakes.size(); i++) {
            System.out.println(i+1 + ". " + cheesecakes.get(i).getText());
        }
    }
 
}