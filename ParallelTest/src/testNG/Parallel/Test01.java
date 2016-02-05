package testNG.Parallel;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

 
public class Test01 extends TestBase{
 
    @Test
    public void testLink()throws Exception{
        getDriver().get("http://localhost/lists.html");
        entertext(getDriver(), "firstname", "Prasanna");
        entertext(getDriver(), "lastname", "Hegde");
        entertext(getDriver(), "email", "phegde@gmail.com");
        entertext(getDriver(), "phone", "976812870");
        entertext(getDriver(), "address", "Airoli");
        clickbutton(getDriver(), "btnsubmit");
        String htmltext =getDriver().findElement(By.tagName("body")).getText();
        if (htmltext.contains("Prasanna"))
        {
          System.out.println(htmltext);
        }
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screeht2.png"));
        Thread.sleep(2000);

    }
    
    private void entertext(WebDriver drv,String searchBy, String txt)
    {
    	WebElement textBox = drv.findElement(By.name(searchBy));
        textBox.click();
        textBox.sendKeys(txt);
    }
    
    private void clickbutton(WebDriver drv,String serachvalue)
    {
       WebElement button = drv.findElement(By.name(serachvalue));
       button.click();
    }
    
}