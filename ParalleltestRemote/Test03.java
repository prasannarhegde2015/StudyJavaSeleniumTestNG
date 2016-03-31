package TESTNG.Parallel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
 
public class Test03  extends TestBase{
	 StringBuilder  sb = new StringBuilder();
	 int cntr = 1;
    @Test
   public void testLink2()throws Exception
   
   {

        getDriver().get("http://meinwessqalw01:8080/squash/login");
        WebElement textBox = getDriver().findElement(By.id("j_username"));
        WebElement textBox2 = getDriver().findElement(By.id("j_password"));
        WebElement lbtn= getDriver().findElement(By.xpath("//input[@value='Sign in']"));
        textBox.click();
        textBox.sendKeys("prasanna");
        textBox2.click();
        textBox2.sendKeys("test@123");
        lbtn.click();
        Thread.sleep(3000);
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        File resimfile = new File("c:\\tmp\\screenshot2.png");
        if( resimfile.exists() == true)
        {
        	WritetoLog("Deleting img file..whose time stamp is "+ new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(new Date(resimfile.lastModified())) );
        	resimfile.delete();
        	WritetoLog("Deleted  old copy of img file..");
        }
        else
        	
        {
        	 WritetoLog("File was not exisitng before ");
        }
        WritetoLog("File still is there ? before copy Expected [False]: "+resimfile.exists());
          FileUtils.copyFile(scrFile, resimfile);
          WritetoLog(String.format("File still is there ? : after copy Expected [True] %s and Modfuied DAte %s: ",resimfile.exists(),new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(new Date(resimfile.lastModified()))));
           if (getDriver().getPageSource().contains("Prasanna"))
           {
        	     
        	    WritetoLog("test has passed");
        	    WritetHTMLRow( Integer.toString(cntr), "TC_01","TC1_step_3","LogoutLink","Prasanna","Prasanna");
        	}
           else
           {
        	   WritetHTMLRow( Integer.toString(cntr), "TC_01","TC1_step_3","LogoutLink","Prasanna"," Not found Expected");
        	   WritetoLog("test has failed");
           }
           genHTMLTABLE();
           
        Thread.sleep(2000);
    }
    
   public void  WritetoLog(String strlogmsg) throws IOException
    {
    	File resultcsvfile = new File("c:\\results.txt");
    	if (!resultcsvfile.exists()) 
    	{
			try 
			{
				System.out.println("File is  not there Creating File");
				resultcsvfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        FileWriter writer = null;
		writer = new FileWriter(resultcsvfile,true);
	    BufferedWriter bwriter = new BufferedWriter(writer);
		bwriter.append(strlogmsg);
		bwriter.newLine();
	    bwriter.close();
    }

   public void  WritetoLog(String FileNametoWriote, String strlogmsg) throws IOException
   {
   	File resultcsvfile = new File(FileNametoWriote);
   	if (!resultcsvfile.exists()) 
   	{
			try 
			{
				System.out.println("File is  not there Creating File");
				resultcsvfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
       FileWriter writer = null;
		writer = new FileWriter(resultcsvfile,true);
	    BufferedWriter bwriter = new BufferedWriter(writer);
		bwriter.append(strlogmsg);
		bwriter.newLine();
	    bwriter.close();
   }
   
   public void  WritetHTMLRow(String srnum, String tcid, String tcstep, String field, String exp, String act) throws IOException
   {
   	    ZonedDateTime now = ZonedDateTime.now();
   	    String strnow = now.toString();
	    sb.append("<tr>");
		sb.append("<td>"+srnum+"</td>");
		sb.append("<td>"+ strnow +"</td>");
		sb.append("<td>"+tcid+"</td>");
		sb.append("<td>"+tcstep+"</td>");
		sb.append("<td>"+field+"</td>");
		sb.append("<td>"+exp+"</td>");
		sb.append("<td>"+act+"</td>");
		if (exp == act )
		{
		sb.append("<td> <font color='green' >Pass</td>");
		}
		else
		{
			sb.append("<td><font color='red' >Fail</td>");
		}
		sb.append("</tr>");
		cntr++;
		
   }
    
   public void writeTextFile(String strtestcase,String strdetails,String strstatus)  throws Exception

    
    {
		File file = new File("c:\\Report.CSV");
		if (!file.exists())
		{
		  file.createNewFile();
			
		}
		FileWriter fileWritter = null;
        fileWritter = new FileWriter(file, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		FileReader reader=null;
		reader = new FileReader(file);
		BufferedReader buffreader = new BufferedReader(reader);
		
		if (  getstreamlength(buffreader) == 0 ) 
			{
				bufferWritter.append( '\u0022' + "test case" + '\u0022' + ","+'\u0022' + "Details" + '\u0022' + ","+'\u0022' + "Status" + '\u0022' + ",");
			}
			bufferWritter.append('\u0022' + strtestcase + '\u0022' + ","+'\u0022' + strdetails + '\u0022' + ","+'\u0022' + strstatus + '\u0022' + ",");
			bufferWritter.newLine();
			bufferWritter.close();
		
		}
		
   public void genHTMLTABLE() throws IOException
   {
      StringBuilder sbh = new StringBuilder();
      
      sbh.append("<html>");
      sbh.append("<head>");
      sbh.append("</head>");
      sbh.append("<body>");
      sbh.append("<b> Overall Summary <b> ");
      sbh.append("<br>");
      sbh.append("<table  border='1' >");
      sbh.append("<tr>");
      sbh.append("<td><b> Sr No </b> </td>");
      sbh.append("<td><b> TimeStamp </b> </td>");
      sbh.append("<td><b> Testcase ID </b> </td>");
      sbh.append("<td><b> Test case Step </td>");
      sbh.append("<td><b> Field </b> </td>");
      sbh.append("<td><b> Expected </b> </td>");
      sbh.append("<td><b> Actual </b></td>");
      sbh.append("<td><b> Result</b> </td>");
      sbh.append("</tr>");
      sbh.append(sb.toString());
      sbh.append("</table>");
      sbh.append("</body>");
      sbh.append("</html>");
      WritetoLog( "c:\\results.html", sbh.toString());
      
   }
   
   
   public int getstreamlength(BufferedReader  rdr) throws Exception 
       {
    	   int streeamlength=0;
   		try
   		{
   			streeamlength=rdr.readLine().toString().length();
   		}
   		catch (Exception e2)
   		{
   				streeamlength=0;
   		}
        finally
        {
        	//return streeamlength;
        }
   		return streeamlength;
       }
	

}