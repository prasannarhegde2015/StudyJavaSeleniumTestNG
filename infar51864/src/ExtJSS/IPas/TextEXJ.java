package ExtJSS.IPas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TextEXJ {

	
	public void jsscanner( WebDriver dr, String jsfile) throws FileNotFoundException
	{
		
	JavascriptExecutor jse = (JavascriptExecutor) dr;  
	Scanner sc = new Scanner((Readable) new FileInputStream(new File(jsfile)));
    String js_TxtFile = ""; 
        while (sc.hasNext()) {          
            String[] s = sc.next().split("\r\n");   
            for (int i = 0; i < s.length; i++) {
                js_TxtFile += s[i];
                js_TxtFile += " ";
            }           
        }
        sc.close();
    String title =  (String) jse.executeScript(js_TxtFile);
    System.out.println("Title  : "+title);
	}
	
	public String  retstringjsscanner( WebDriver dr, String jsfile) throws FileNotFoundException
	{
		
	JavascriptExecutor jse = (JavascriptExecutor) dr;  
	Scanner sc1 = new Scanner((Readable) new FileInputStream(new File(jsfile)));
    String js_TxtFile = ""; 
        while (sc1.hasNext()) {          
            String[] s = sc1.next().split("\r\n");   
            for (int i = 0; i < s.length; i++) {
                js_TxtFile += s[i];
                js_TxtFile += " ";
            }           
        }
       // sc1.close();
        sc1.close();
    String title =  (String) jse.executeScript(js_TxtFile);
    System.out.println("Title  : "+title);
    return title;
	}
}
