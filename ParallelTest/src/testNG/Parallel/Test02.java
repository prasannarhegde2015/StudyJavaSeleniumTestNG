package testNG.Parallel;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.*;
 
public class Test02 extends TestBase{
 
    @Test
    public void testLink()throws Exception{
        getDriver().get("http://localhost/phpBB3/index.php");
        WebElement textBox = getDriver().findElement(By.name("username"));
        textBox.click();
        textBox.sendKeys("phegde");
     //   getDriver().findElement(By.name("username"));
         File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
         FileUtils.copyFile(scrFile, new File("c:\\tmp\\screeht1.png"));
        Thread.sleep(2000);
        Assert.assertEquals(textBox.isDisplayed(), true);
    }
 
}