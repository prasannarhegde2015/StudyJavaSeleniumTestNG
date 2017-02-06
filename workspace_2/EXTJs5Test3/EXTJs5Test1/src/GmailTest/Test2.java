package GmailTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import CommonFunctions.Helper;

public class Test2 {
	
	
	
	
	@Test
	
	public void test2() throws IOException
	{
	         File myfile = new File("d:\\test2.txt");
	         FileOutputStream out = new FileOutputStream(myfile);
	         String test = "["+new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date())+"] Test2 Method was executed ";
	         byte[] b = test.getBytes();
	         out.write(b);
	         out.close();
	}
	




     
}
