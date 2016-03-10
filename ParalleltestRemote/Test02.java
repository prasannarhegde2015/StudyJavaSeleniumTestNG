package TESTNG.Parallel;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.*;
 
public class Test02 extends TestBase{
 
    @Test
    public void testLink()throws Exception{
        getDriver().get("http://meinwessqalw:8080/login.do");
        WebElement textBox = getDriver().findElement(By.xpath("//input[@name='username']"));
        WebElement textBox2 = getDriver().findElement(By.xpath("//input[@name='pwd']"));
        WebElement lbtn= getDriver().findElement(By.xpath("//a[@id='loginButton']"));
        textBox.click();
        textBox.sendKeys("Prasanna");
        textBox2.click();
        textBox2.sendKeys("test@123");
        lbtn.click();
        Thread.sleep(3000);
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
     // Now you can do whatever you need to do with it, for example copy somewhere
        if( new File("c:\\tmp\\screenshot1.png").exists() == true)
        {
        	new File("c:\\tmp\\screenshot1.png").delete();
        }
       FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot1.png"));
        Thread.sleep(2000);
    }
 
}