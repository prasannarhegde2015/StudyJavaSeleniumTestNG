package TESTNG.Parallel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
 
public class Test05 extends TestBase{
 
    @Test
    public void testLink()throws Exception{
        getDriver().get("http://facebook.com");
        WebElement textBox = getDriver().findElement(By.xpath("//input[@value='Re-enter Email']"));
        textBox.click();
        textBox.sendKeys("Test 5!");
        Thread.sleep(2000);
    }
}