package TESTNG.Parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
 
public class Test04  extends TestBase{
 
    @Test
    public void testLink()throws Exception{
        getDriver().get("https://twitter.com");
        WebElement textBox = getDriver().findElement(By.xpath("//label[text()='Full name']"));
        textBox.click();
        textBox.sendKeys("Test 4!");
        Thread.sleep(2000);
    }
}