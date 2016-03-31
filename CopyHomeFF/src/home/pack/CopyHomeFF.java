package home.pack;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import home.pack.Object;

//Read more: http://mrbool.com/reading-excel-file-with-java/24562#ixzz2FET7Wbwz
public class CopyHomeFF {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
  
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 WebDriver drv = new FirefoxDriver();
		 Object objlib = new Object();
         drv.navigate().to("http://localhost/phpBB3/index.php");
         objlib.AddData("LoginH.xls","tc_1", drv);
        
	}


}
	






