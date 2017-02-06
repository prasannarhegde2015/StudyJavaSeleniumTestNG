package CommonFunctions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	 private String name;

	    public String getName() { return this.name; }
	    public void setName(String name) { this.name = name; }
	public Properties readpropertiesfileall ()
	{
		Properties properties = new Properties();
		try {
			File file = new File(this.name);
			String rlocation = System.getProperty("user.dir") + "\\src\\ObjectLibrary";
			FileInputStream fileInput = new FileInputStream(rlocation+ "\\"+file );
		//	System.out.println("Reading File from location :"+rlocation+ "\\"+file);
			properties.load(fileInput);
			fileInput.close();
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			return properties;
		}
	  
	}
	
	public String readpropertiesfile (String keyname)
	{
		String retkeyvalue = "";
		try {
			File file = new File(this.name);
		//	System.out.println("File name used is:"+this.name);
		//	System.out.println(System.getProperty("user.dir"));
			String rlocation = System.getProperty("user.dir") + "\\src\\ObjectLibrary";
			FileInputStream fileInput = new FileInputStream(rlocation+ "\\"+file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
            
			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				if ( key.contains(keyname))
				{
					retkeyvalue  = properties.getProperty(key);
					break;
				}
				
				
			}
			System.out.println("returned value "+retkeyvalue);
			return retkeyvalue;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return retkeyvalue;
		} catch (IOException e) {
			e.printStackTrace();
			return retkeyvalue;
		}
	  
	}

	
	public void CaptureScreesnhot(WebDriver drv , String filename) throws IOException
	{
		 File src  =   ((TakesScreenshot)drv).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(src, new File(filename));
	}
}
