package ExtJSS.IPas;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;

public class FillFormTestFinal {
	public String baseUrl = "http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#form-checkout";
	public WebDriver driver;

	@Before
	public void setBaseURL() {
		System.setProperty("webdriver.ie.driver", "D:\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@Test
	public void verifyCheckoutForm() {

		/*w
		 * Waiting for form to be load it takes time to load, hence added wait
		 * condition
		 */
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.cssSelector(".x-title-text.x-title-text-default-framed.x-title-item"),
				"Complete Check Out"));
		
		/*
		 * Input boxes will have dynamic ids but, has unique name attribute so
		 * locating them using name is way to go 
		 **/
		driver.findElement(By.name("firstName")).sendKeys("TestFirst");
		driver.findElement(By.name("lastName")).sendKeys("TestLast");
		driver.findElement(By.name("email")).sendKeys("test@test.com");
		driver.findElement(By.name("phone")).sendKeys("123-123-1234");
		driver.findElement(By.name("mailingStreet")).sendKeys("Street Test Address");
		driver.findElement(By.name("mailingCity")).clear();
		driver.findElement(By.name("mailingCity")).sendKeys("NewYork");

		/*
		 *  This state drop down is not normal dropdown hence, first clicked on textbox of dropdown to get focus then,
		 *  clicked on down arrow using css class names which will show list of state name
		 *  then select state by locating li by its contain
		 *  same strategy applied for month dropdown 
		 */
		/* State in Mailing Address */
		driver.findElement(By.name("mailingState")).click();
		driver.findElement(By.xpath("//div[@class='x-form-trigger x-form-trigger-default x-form-arrow-trigger x-form-arrow-trigger-default  x-form-trigger-focus']")).click();												  												   

		//driver.findElement(By.xpath("//div[@class='x-boundlist-list-ct x-unselectable']/ul/li[contains(.,'Arizona')]")).click();
		driver.findElement(By.xpath("//div[@class='x-boundlist  x-boundlist-floating x-layer x-boundlist-default x-border-box']/div/ul/li[contains(.,'Arizona')]")).click();
		
		driver.findElement(By.name("mailingPostalCode")).sendKeys("4007091234");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* ----------------- Billing Address --------------*/
		driver.findElement(By.xpath("//fieldset[3]/div/div/div/div/div/div/input")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name("billingStreet")).clear();
		driver.findElement(By.name("billingCity")).clear();
		driver.findElement(By.name("billingCity")).sendKeys("England");
		
		/* State in Billing Address */
		driver.findElement(By.name("billingState")).click();
		driver.findElement(
				By.xpath("//div[@class='x-form-trigger x-form-trigger-default x-form-arrow-trigger x-form-arrow-trigger-default  x-form-trigger-focus']"))
				.click();

		//driver.findElement(By.xpath("//div[@class='x-boundlist-list-ct x-unselectable']/ul/li[contains(.,'Delaware')]")).click();
		driver.findElement(By.xpath("//div[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box x-boundlist-above']/div/ul/li[contains(.,'Delaware')]")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.name("billingPostalCode")).sendKeys("4007091234");
		
		/* ----------------- Billing Address --------------*/
		driver.findElement(By.name("ccName")).sendKeys("Test Name");
		driver.findElement(By.name("ccNumber")).sendKeys("123456789123456");
		
		driver.findElement(By.name("ccExpireMonth")).click();
		driver.findElement(By.xpath("//div[@class='x-form-trigger x-form-trigger-default x-form-arrow-trigger x-form-arrow-trigger-default  x-form-trigger-focus']")).click();
		driver.findElement(By.xpath("//div[@class='x-boundlist-list-ct x-unselectable']/ul/li[contains(.,'May')]")).click();
		
		driver.findElement(By.name("ccExpireYear")).sendKeys("2021");
		
		driver.findElement(By.linkText("Complete Purchase")).click();
		driver.findElement(By.linkText("Reset")).click();

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
