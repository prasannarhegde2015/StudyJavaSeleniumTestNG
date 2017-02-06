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

import org.junit.Test;

import CommonFunctions.Helper;

public class TestFileWrite {
	
	
	@Test
	public void test()
	{
	Helper hlp = new Helper();
	hlp.setName("MailPage.Properties");
	String prstvalue = hlp.readpropertiesfileall().getProperty("linkmymail");
	String origvalue = prstvalue;
	System.out.println("Before"+prstvalue);
	prstvalue = prstvalue.replace("<placeholder>", "mytestvalue");
	System.out.println("After"+prstvalue);
	hlp.updatePropertyValue("linkmymail", prstvalue);
	System.out.println("After Reading it "+hlp.readpropertiesfileall().getProperty("linkmymail"));
	//
	//
	//
	//hlp.updatePropertyValue("linkmymail", origvalue);
	}
	
	@Test
	
	public void test2() throws IOException
	{
	         File myfile = new File("d:\\test.txt");
	         FileOutputStream out = new FileOutputStream(myfile);
	         String test = "New String";
	         byte[] b = test.getBytes();
	         out.write(b);
	         out.close();
	}
	
@Test
	
	public void test3() throws IOException
	{
	         File myfile = new File("d:\\file1.txt");
	         FileReader rdr = new FileReader(myfile);
	         BufferedReader br = new BufferedReader(rdr);
	         String line = null;
	         StringBuilder sb = new StringBuilder();
	         while ( (line = br.readLine()) != null)
	         {
	        	 sb.append(line);
	        	sb.append(System.lineSeparator());
	         }
	         
	         System.out.println("Out put is"+sb.toString());

	}
       

public void test4() throws IOException
{
	Helper hlp = new Helper();
	hlp.setName("MailPage.Properties");
	String prstvalue = hlp.readpropertiesfileall().getProperty("tabstrip");
	System.out.println("After Reading it "+hlp.readpropertiesfileall().getProperty("tabstrip"));
	//

}


    private String getStringFromInputStream(InputStream is) 
{

	BufferedReader br = null;
	StringBuilder sb = new StringBuilder();

	String line;
	try {

		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(br);
			//sb.append("\r\n ");
		}

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	return sb.toString();

}

     
}
